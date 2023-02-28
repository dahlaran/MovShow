package com.dahlaran.movshow.movies.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dahlaran.movshow.utils.data.DataState
import com.dahlaran.movshow.movies.data.TVMazeRepository
import com.dahlaran.movshow.movies.models.Media
import com.dahlaran.movshow.utils.launchRepositoryCall
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MediaDetailViewModel @Inject constructor(var mediaRepository: TVMazeRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    val media: MutableLiveData<Media> = MutableLiveData()
    val dataLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun start(mediaId: Int) {
        launchGetMediaWithID(mediaId)
    }

    fun refresh() {
        val mediaId = media.value?.id
        if (mediaId != null) {
            launchGetMediaWithID(mediaId)
        } else {
            dataLoading.value = false
        }
    }

    private fun launchGetMediaWithID(mediaId: Int) {
        if (dataLoading.value != true) {
            launchRepositoryCall {
                mediaRepository.searchMediaById(mediaId.toString()).onEach { dataState ->
                    when (dataState) {
                        DataState.Loading -> {
                            dataLoading.postValue(true)
                        }
                        is DataState.Error -> {
                            dataLoading.postValue(false)
                        }
                        is DataState.LoadingData -> {
                            // Do Nothing, implement this when adding database
                        }
                        is DataState.Success -> {
                            media.postValue(dataState.data)
                            dataLoading.postValue(false)
                        }
                    }
                }.launchIn(this)
            }
        }
    }

    override fun onCleared() {
        // Remove observable if viewModel is destroyed
        disposable.dispose()
        super.onCleared()
    }
}