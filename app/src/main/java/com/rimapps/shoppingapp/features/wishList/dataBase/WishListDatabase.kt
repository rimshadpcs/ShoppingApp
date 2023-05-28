package com.rimapps.shoppingapp.features.wishList.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rimapps.shoppingapp.features.wishList.data.FavouriteProductEntity

@Database(entities = [FavouriteProductEntity::class], version = 1)
abstract class WishListDatabase : RoomDatabase() {

    abstract fun wishListDao():WishListDao
}