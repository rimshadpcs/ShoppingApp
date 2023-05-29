package com.rimapps.shoppingapp.features.wishList.usecase

import com.rimapps.shoppingapp.features.wishList.repository.WishListRepository
import javax.inject.Inject

class IsProductInWishListUseCase  @Inject constructor(
    private val wishListRepository: WishListRepository
){
    suspend fun execute(productId: String): Boolean =
        wishListRepository.isFavourite(productId)
}