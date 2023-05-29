package com.rimapps.shoppingapp.shared.data.repository

import com.rimapps.shoppingapp.features.productDetails.business.ProductDetail
import com.rimapps.shoppingapp.features.productList.business.Product
import com.rimapps.shoppingapp.features.productDetails.data.AsyncResult


interface ProductRepository  {

    suspend fun getProductList(): AsyncResult<List<Product>>
    suspend fun getProductDetails(productId: String): ProductDetail

}