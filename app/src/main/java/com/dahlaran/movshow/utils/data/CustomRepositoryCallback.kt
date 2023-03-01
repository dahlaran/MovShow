package com.dahlaran.movshow.utils.data

import timber.log.Timber
import kotlin.reflect.KFunction

class CustomRepositoryCallback<out T>(
    private val function: KFunction<*>,
    private val onLoading: (() -> Unit)? = null,
    private val onSuccess: ((T) -> Unit)? = null,
    private val onError: ((DataState.Error) -> Unit)? = null,
    private val onLoadingData: ((T) -> Unit)? = null
) : RepositoryCallback {

    override fun onLoading() {
        Timber.d("Loading %s", function.name)
        onLoading?.invoke()
    }

    override fun onSuccess() {
        Timber.d("Success %s", function.name)
        onLoading?.invoke()

    }

    override fun onDelete() {
        Timber.d("Delete %s", function.name)
        onLoading?.invoke()

    }

    override fun onLoadingData() {
        Timber.d("LoadingData %s", function.name)

        onLoading?.invoke()
    }
}