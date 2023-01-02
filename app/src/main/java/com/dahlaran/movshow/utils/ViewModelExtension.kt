package com.dahlaran.movshow.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.launchRepositoryCall(block: suspend CoroutineScope.() -> Unit){

    val coroutineScope = CoroutineScope(Dispatchers.Default + Job())

    coroutineScope.launch {
        block.invoke(this)
    }
}