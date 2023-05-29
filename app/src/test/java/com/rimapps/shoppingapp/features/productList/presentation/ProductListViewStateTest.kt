package com.rimapps.shoppingapp.features.productList.presentation

import org.junit.Test


class ProductListViewStateTest{

    @Test
    fun `Correct product viewState is updated when it is favourite`(){
        val content = ProductListViewState.Content(
            productList = (0..9).map{
                ProductCardViewState(
                    it.toString(),
                    "",
                    "",
                    "",
                    "",
                    false

                )
            }
        )
        val result = content.updateFavoriteProduct(
            "4",true
        )
        assert(result.productList[4].isFavourite)

    }

    @Test
    fun `Correct product ViewState is updated when it is not favourite`(){
        val content = ProductListViewState.Content(
            productList = (1..10).map {
                ProductCardViewState(
                    it.toString(),
                    "",
                    "",
                    "",
                    "",
                    false
                )

            }
        )
        val result = content.updateFavoriteProduct(
            "2",
            false
        )

        assert(!result.productList[2].isFavourite)
    }
}