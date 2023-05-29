package com.rimapps.shoppingapp.features.wishList.usecase

import com.rimapps.shoppingapp.features.wishList.repository.WishListRepository
import javax.inject.Inject

class AddOrRemoveFromWishListUseCase @Inject constructor(
    private val wishListRepository: WishListRepository,
    private val isProductInWishListUseCase: IsProductInWishListUseCase
) {
    suspend fun execute(productId:String){
        if(isProductInWishListUseCase.execute(productId)){
            wishListRepository.removeFromWishList(productId)
        }
        else{
            wishListRepository.addToWishList(productId)
        }
    }



}