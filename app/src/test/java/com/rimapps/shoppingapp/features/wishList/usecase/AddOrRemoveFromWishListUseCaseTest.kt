package com.rimapps.shoppingapp.features.wishList.usecase

import com.rimapps.shoppingapp.features.wishList.repository.WishListRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddOrRemoveFromWishListUseCaseTest{

    private val isProductInWishListUseCase = mockk<IsProductInWishListUseCase>()
    private val wishListRepository = mockk<WishListRepository>(relaxed = true)
    private lateinit var  useCase: AddOrRemoveFromWishListUseCase

    @Before
    fun setup(){
        useCase = AddOrRemoveFromWishListUseCase(
            wishListRepository,
            isProductInWishListUseCase
        )
    }

    @Test
    fun `product is not in wishlist and add method should work`() = runTest {

        coEvery {
            isProductInWishListUseCase.execute(any())
        }returns false
        useCase.execute("123")

        coVerify {
             wishListRepository.addToWishList("123")
        }

    }
    @Test
    fun `product is not in wishlist and remove method should work`() = runTest {

        coEvery {
            isProductInWishListUseCase.execute(any())
        }returns true

        useCase.execute("123")

        coVerify {
            wishListRepository.removeFromWishList("123")
        }

    }
}