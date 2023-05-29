package com.rimapps.shoppingapp.features.productDetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rimapps.shoppingapp.features.productDetails.business.ProductDetail
import com.rimapps.shoppingapp.features.productList.presentation.ProductListViewState
import com.rimapps.shoppingapp.shared.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<ProductDetailsViewState>(ProductDetailsViewState.Loading)
    val viewState: MutableStateFlow<ProductDetailsViewState>
        get() = _viewState

    fun loadProduct(productId: String) {
        viewModelScope.launch {
            _viewState.value=ProductDetailsViewState.Loading
            // Data call to fetch products
            val productDetails = repository.getProductDetails(productId)
            _viewState.value=ProductDetailsViewState.Content(productDetails)
        }
    }
}