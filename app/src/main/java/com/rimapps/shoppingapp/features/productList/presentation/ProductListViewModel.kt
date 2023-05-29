package com.rimapps.shoppingapp.features.productList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rimapps.shoppingapp.features.productDetails.data.AsyncResult
import com.rimapps.shoppingapp.shared.data.repository.ProductRepository
import com.rimapps.shoppingapp.features.wishList.usecase.AddOrRemoveFromWishListUseCase
import com.rimapps.shoppingapp.features.wishList.usecase.IsProductInWishListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase,
    private val isProductInWishListUseCase: IsProductInWishListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

) : ViewModel() {

    private val _viewState = MutableStateFlow<ProductListViewState>(ProductListViewState.Loading)
    val viewState: Flow<ProductListViewState>
        get() = _viewState


    fun loadProductList() {
        viewModelScope.launch(dispatcher) {
            _viewState.value = ProductListViewState.Loading
            // Data call to fetch products
            when (val productList = repository.getProductList()) {
                is AsyncResult.Error -> _viewState.value = ProductListViewState.Error

                is AsyncResult.Success ->
                    _viewState.value = ProductListViewState.Content(productList.data.map {
                        ProductCardViewState(
                            it.productId,
                            it.title,
                            it.description,
                            "US $ ${it.price}",
                            it.imageUrl,
                            isProductInWishListUseCase.execute(it.productId)
                        )
                    })
            }
        }
    }

    fun favouriteIconClicked(productId: String) {
        viewModelScope.launch(dispatcher) {
            addOrRemoveFromWishListUseCase.execute(productId)
            val currentViewState = _viewState.value
            (currentViewState as? ProductListViewState.Content)?.let { content ->
                _viewState.value
                content.updateFavoriteProduct(
                    productId, isProductInWishListUseCase.execute(productId)
                )
            }

        }
    }
}
