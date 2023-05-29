package com.rimapps.shoppingapp.features.productDetails.presentation

import com.rimapps.shoppingapp.features.productDetails.business.ProductDetail


sealed class ProductDetailsViewState {
    object Loading : ProductDetailsViewState()
    data class Content(val product: ProductDetail) : ProductDetailsViewState()
    object Error : ProductDetailsViewState()
}
