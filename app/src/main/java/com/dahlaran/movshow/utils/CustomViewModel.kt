package com.dahlaran.movshow.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dahlaran.movshow.utils.data.DataState
import com.dahlaran.movshow.utils.data.Error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import kotlin.reflect.KFunction

open class CustomViewModel : ViewModel() {

    val dataLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun <T> launchRepositoryCall(
        result: Flow<DataState<T>>,
        function: KFunction<*>,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Error) -> Unit)? = null,
        onLoadingData: ((T) -> Unit)? = null
    ) {

        result.onEach {
            when (it) {
                is DataState.Error -> {
                    Timber.d("Error %s", function.name)
                    onError?.invoke(it.error)
                    dataLoading.postValue(false)
                }
                DataState.Loading -> {
                    Timber.d("Loading %s", function.name)
                    onLoading?.invoke()
                    dataLoading.postValue(true)
                }
                is DataState.LoadingData -> {
                    Timber.d("LoadingData %s", function.name)
                    onLoadingData?.invoke(it.data)
                }
                is DataState.Success -> {
                    Timber.d("Success %s", function.name)
                    onSuccess?.invoke(it.data)
                    dataLoading.postValue(false)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.Default + Job()))
    }
}