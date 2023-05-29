package com.rimapps.shoppingapp.features.productDetails.business

data class ProductDetail(
    val title: String,
    val description: String,
    val fullDescription: String,
    val price: String,
    val imageUrl : String,
    val pros : List<String>,
    val cons : List<String>
) {
}