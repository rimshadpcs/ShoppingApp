package com.rimapps.shoppingapp.features.productList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rimapps.shoppingapp.features.wishList.repository.WishListRepository
import com.rimapps.shoppingapp.features.productList.repository.ProductRepository
import com.rimapps.shoppingapp.features.wishList.usecase.AddOrRemoveFromWishListUseCase
import com.rimapps.shoppingapp.features.wishList.usecase.IsProductInWishListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase,
    private val isProductInWishListUseCase: IsProductInWishListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(
) {
    private val _viewState = MutableLiveData<ProductListViewState>()

    val viewState: LiveData<ProductListViewState>
        get() = _viewState

    fun loadProductList() {

        viewModelScope.launch {
            _viewState.postValue(ProductListViewState.Loading)
            val productList = repository.getProductList()
            _viewState.postValue(ProductListViewState.Content(productList.map {
                ProductCardViewState(
                    it.productId,
                    it.title,
                    it.description,
                    "US $ ${it.price}",
                    it.imageUrl,
                    isProductInWishListUseCase.execute(it.productId)
                )
            }))

        }
    }

    fun favouriteIconClicked(productId: String) {
        viewModelScope.launch(dispatcher) {
            addOrRemoveFromWishListUseCase.execute(productId)
            val currentViewState = _viewState.value
            (currentViewState as? ProductListViewState.Content)?.let { content ->
                _viewState.postValue(
                content.updateFavoriteProduct(
                    productId,
                    isProductInWishListUseCase.execute(productId)
                )
                )
            }

        }
    }
}