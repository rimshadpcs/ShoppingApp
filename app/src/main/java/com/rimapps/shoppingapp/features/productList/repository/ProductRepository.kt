package com.rimapps.shoppingapp.features.productList.repository

import com.rimapps.shoppingapp.ProductCardViewState
import retrofit2.http.GET

interface ProductRepository  {

    suspend fun getProductList():List<ProductCardViewState>
}