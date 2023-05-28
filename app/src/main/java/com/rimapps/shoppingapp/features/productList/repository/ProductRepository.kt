package com.rimapps.shoppingapp.features.productList.repository

import com.rimapps.shoppingapp.features.productList.business.Product


interface ProductRepository  {

    suspend fun getProductList():List<Product>
}