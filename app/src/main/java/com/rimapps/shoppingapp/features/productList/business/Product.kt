package com.rimapps.shoppingapp.features.productList.business

data class Product(
    val title: String,
    val description: String,
    val price: Double,
    val imageUrl : String,
    val productId: String
)