package com.rimapps.shoppingapp.features.wishList.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteProductEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "product_name") val productName: String
) {
}