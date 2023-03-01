package com.dahlaran.movshow.utils.data

interface RepositoryCallback {
    fun onLoading()
    fun onSuccess()
    fun onDelete()
    fun onLoadingData()
}