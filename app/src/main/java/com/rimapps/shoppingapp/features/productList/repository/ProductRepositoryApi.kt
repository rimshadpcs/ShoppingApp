package com.rimapps.shoppingapp.features.productList.repository

import com.rimapps.shoppingapp.ProductCardViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryApi() : ProductRepository {

    private val client = ApiClient().getClient()

    override suspend fun getProductList(): List<ProductCardViewState> {
        return withContext(Dispatchers.IO) {
            client.getProductList().map {
                ProductCardViewState(
                    it.title,
                    it.description,
                    "USD ${it.price}",
                    it.imageUrl

                )
            }

        }
    }
}