package com.dahlaran.movshow.utils.data

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class LoadingData<out T>(val data: T) : DataState<T>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val error: com.dahlaran.movshow.utils.data.Error) : DataState<Nothing>()
}