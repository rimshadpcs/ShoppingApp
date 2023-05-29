package com.rimapps.shoppingapp.shared.data.repository.api

import com.rimapps.shoppingapp.features.productDetails.business.ProductDetail
import com.rimapps.shoppingapp.features.productList.business.Product
import com.rimapps.shoppingapp.features.productDetails.data.AsyncResult
import com.rimapps.shoppingapp.shared.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryApi @Inject constructor(private val service: ProductService) :
    ProductRepository {

    override suspend fun getProductList(): AsyncResult<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                AsyncResult.Success(
                service.getProductList().map {
                    Product(
                        it.title,
                        it.description,
                        it.price,
                        it.imageUrl,
                        it.id
                    )
                })

            }
            catch (exception:Exception){
                AsyncResult.Error(exception)
            }
        }
    }
    override suspend fun getProductDetails(productId: String): ProductDetail {
        return withContext(Dispatchers.IO){
            service.getProductDetails(productId).run {
                ProductDetail(
                    this.title,
                    this.description,
                    this.full_description,
                    "US $ ${this.price}",
                    this.imageUrl,
                    this.pros,
                    this.cons
                )
            }

        }
    }
}