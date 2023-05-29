package com.rimapps.shoppingapp.features.productDetails.data

sealed class AsyncResult<out T> {
    data class Error(val exception: Exception): AsyncResult<Nothing>()
    data class Success<T>(val data:T): AsyncResult<T>()
}