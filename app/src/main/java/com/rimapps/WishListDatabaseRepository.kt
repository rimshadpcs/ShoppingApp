package com.rimapps

import com.rimapps.shoppingapp.features.WishListRepository
import javax.inject.Inject

class WishListDatabaseRepository @Inject constructor() : WishListRepository {

    override fun isFavourite(productId: String): Boolean {
        return true
    }

    override fun addToWishList(productId: String) {
    }

    override fun removeFromWishList(productId: String) {
    }
}