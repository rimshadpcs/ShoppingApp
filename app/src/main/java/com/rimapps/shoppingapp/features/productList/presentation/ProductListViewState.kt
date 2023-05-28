package com.rimapps.shoppingapp.features.productList.presentation

sealed class ProductListViewState {

    object Loading: ProductListViewState()

    data class Content(val productList:List<ProductCardViewState>): ProductListViewState()

    object Error: ProductListViewState()

}
fun ProductListViewState.Content.updateFavoriteProduct(
    productId: String,
    isFavorite: Boolean
): ProductListViewState.Content {
    return ProductListViewState.Content(productList = this.productList.map { viewState ->
        if (viewState.id == productId) {
            viewState.copy(isFavourite = isFavorite)
        } else {
            viewState
        }
    }
    )
}
