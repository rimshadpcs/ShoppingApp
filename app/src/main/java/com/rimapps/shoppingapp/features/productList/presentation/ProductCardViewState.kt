package com.rimapps.shoppingapp.features.productList.presentation

data class ProductCardViewState(
    val id:String,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl : String,
    val isFavourite: Boolean
)