package com.rimapps.shoppingapp.features.wishList.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rimapps.shoppingapp.features.wishList.data.FavouriteProductEntity

@Dao
interface WishListDao{

    @Query("SELECT * FROM favouriteproductentity WHERE id=:id")
    fun isProductFavourite(id: String): FavouriteProductEntity?

    @Insert
    fun addProductToFavourite(productEntity: FavouriteProductEntity)

    @Delete
    fun removeProductFromFavourite(product: FavouriteProductEntity)
}

