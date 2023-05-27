package com.rimapps.shoppingapp.features.productList.repository

import retrofit2.http.GET

interface ProductService {

   @GET
   suspend fun getProductList():List<ProductCardEntity>
}