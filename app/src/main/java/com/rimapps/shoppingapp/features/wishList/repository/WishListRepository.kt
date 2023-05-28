package com.rimapps.shoppingapp.features.wishList.repository

interface WishListRepository {
    suspend fun isFavourite(productId: String): Boolean

    suspend fun addToWishList(productId: String)

    suspend fun removeFromWishList(productId: String)
}