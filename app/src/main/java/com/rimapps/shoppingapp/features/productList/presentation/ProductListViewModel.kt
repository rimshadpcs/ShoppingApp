package com.rimapps.shoppingapp.features.productList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rimapps.shoppingapp.ProductListViewState
import com.rimapps.shoppingapp.features.productList.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: ProductRepository) : ViewModel(

) {



    private val _viewState = MutableLiveData<ProductListViewState>()

    val viewState: LiveData<ProductListViewState>
        get() = _viewState


    fun loadProductList() {

        viewModelScope.launch {
            _viewState.postValue(ProductListViewState.Loading)
            val productList = repository.getProductList()
            _viewState.postValue(ProductListViewState.Content(productList))

        }
    }
}