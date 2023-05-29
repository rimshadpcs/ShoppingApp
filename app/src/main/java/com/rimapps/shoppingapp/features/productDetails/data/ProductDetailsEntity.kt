package com.rimapps.shoppingapp.features.productDetails.data

data class ProductDetailsEntity(
    val title: String,
    val description: String,
    val full_description: String,
    val price: Double,
    val imageUrl : String,
    val pros : List<String>,
    val cons : List<String>

){

}