package com.rimapps.shoppingapp.features.productList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rimapps.shoppingapp.features.WishListRepository
import com.rimapps.shoppingapp.features.productList.presentation.ProductListViewState
import com.rimapps.shoppingapp.features.productList.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val wishListRepository: WishListRepository
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
                    it.title,
                    it.description,
                    "USD ${it.price}",
                    it.imageUrl,
                    wishListRepository.isFavourite(it.productId)
                )
            }))

        }
    }
}