package com.dahlaran.movshow.movies.viewModel

import androidx.lifecycle.MutableLiveData
import com.dahlaran.movshow.movies.data.TVMazeRepository
import com.dahlaran.movshow.movies.models.Media
import com.dahlaran.movshow.utils.CustomViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaDetailViewModel @Inject constructor(var mediaRepository: TVMazeRepository) :
    CustomViewModel() {
    val media: MutableLiveData<Media> = MutableLiveData()

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
            launchRepositoryCall(mediaRepository.searchMediaById(mediaId.toString()), onSuccess = {
                media.postValue(it)
            })
        }
    }
}