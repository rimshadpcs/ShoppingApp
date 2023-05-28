package com.rimapps.shoppingapp.features.productList.repository

import com.rimapps.shoppingapp.Product


interface ProductRepository  {

    suspend fun getProductList():List<Product>
}