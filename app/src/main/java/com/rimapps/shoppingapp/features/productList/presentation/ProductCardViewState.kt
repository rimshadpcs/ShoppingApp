package com.rimapps.shoppingapp.features.productList.presentation

data class ProductCardViewState
    (
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val isFavourite: Boolean
)