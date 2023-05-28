package com.rimapps.shoppingapp.features.productList.repository.api

import com.rimapps.shoppingapp.features.productList.business.Product
import com.rimapps.shoppingapp.features.productList.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryApi @Inject constructor(private val service: ProductService) :
    ProductRepository {

    override suspend fun getProductList(): List<Product> {
        return withContext(Dispatchers.IO) {
            service.getProductList().map {
                Product(
                    it.title,
                    it.description,
                    it.price,
                    it.imageUrl,
                    it.id
                )
            }

        }
    }
}