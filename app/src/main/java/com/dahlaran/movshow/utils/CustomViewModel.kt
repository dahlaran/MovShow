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

/**
 * Custom View model to reduce code and keep all dataLoading logic inside this class
 */
open class CustomViewModel : ViewModel() {

    val dataLoading: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Function to simplify repository process
     *
     * @param result Flow of DataState
     * @param onLoading Callback call when the state is Loading
     * @param onSuccess Callback call when the state is Success
     * @param onError Callback call when the state is Error
     * @param onLoadingData Callback call when the state is LoadingData (Data from database)
     */
    fun <T> launchRepositoryCall(
        result: Flow<DataState<T>>,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Error) -> Unit)? = null,
        onLoadingData: ((T) -> Unit)? = null
    ) {
        result.onEach {
            when (it) {
                is DataState.Error -> {
                    onError?.invoke(it.error)
                    dataLoading.postValue(false)
                }
                DataState.Loading -> {
                    onLoading?.invoke()
                    dataLoading.postValue(true)
                }
                is DataState.LoadingData -> {
                    onLoadingData?.invoke(it.data)
                }
                is DataState.Success -> {
                    onSuccess?.invoke(it.data)
                    dataLoading.postValue(false)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.Default + Job()))
    }
}