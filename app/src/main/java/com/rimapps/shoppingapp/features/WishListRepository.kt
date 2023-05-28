package com.rimapps.shoppingapp.features

interface WishListRepository {
    fun isFavourite(productId: String): Boolean

    fun addToWishList(productId: String)

    fun removeFromWishList(productId: String)
}