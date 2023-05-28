package com.rimapps.shoppingapp.features.productList.repository.api

import com.rimapps.shoppingapp.features.productList.data.ProductEntity
import retrofit2.http.GET

interface ProductService {

   @GET("products")
   suspend fun getProductList():List<ProductEntity>
}