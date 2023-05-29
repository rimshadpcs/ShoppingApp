package com.rimapps.shoppingapp.shared.data.repository.api

import com.rimapps.shoppingapp.features.productDetails.data.ProductDetailsEntity
import com.rimapps.shoppingapp.features.productList.data.ProductEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

   @GET("products")
   suspend fun getProductList():List<ProductEntity>

   @GET("productDetails")
   suspend fun getProductDetails(@Query("productId") productId: String): ProductDetailsEntity

}