package com.rimapps.shoppingapp.features.productList.presentation

sealed class ProductListViewState {

    object Loading: ProductListViewState()

    data class Content(val productList:List<ProductCardViewState>): ProductListViewState()

    object Error: ProductListViewState()

}
