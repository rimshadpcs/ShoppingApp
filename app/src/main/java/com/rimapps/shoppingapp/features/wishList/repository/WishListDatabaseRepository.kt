package com.rimapps.shoppingapp.features.wishList.repository

import com.rimapps.shoppingapp.features.wishList.data.FavouriteProductEntity
import com.rimapps.shoppingapp.features.wishList.dataBase.WishListDao
import com.rimapps.shoppingapp.features.wishList.repository.WishListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class WishListDatabaseRepository @Inject constructor(
    private val databaseDAO:WishListDao
)  : WishListRepository {

    override suspend fun isFavourite(productId: String): Boolean {
        return withContext(Dispatchers.IO) {
            databaseDAO.isProductFavourite(productId) != null
        }
    }

    override suspend fun addToWishList(productId: String) {
        return withContext(Dispatchers.IO) {
            databaseDAO.addProductToFavourite(
                FavouriteProductEntity(productId,"")
            )
        }
    }

    override suspend fun removeFromWishList(productId: String) {
        return withContext(Dispatchers.IO) {
            databaseDAO.removeProductFromFavourite(
                FavouriteProductEntity(productId,"")
            )
        }
    }

}