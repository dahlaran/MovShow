package com.dahlaran.movshow.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.dahlaran.movshow.movies.data.TVMazeRepository
import com.dahlaran.movshow.movies.models.Media
import com.dahlaran.movshow.movies.utils.sortByAlphabetical
import com.dahlaran.movshow.movies.utils.sortByRatingDescending
import com.dahlaran.movshow.utils.CustomViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaListViewModel @Inject constructor(var mediaRepository: TVMazeRepository) :
    CustomViewModel() {

    val medias: MutableLiveData<List<Media?>> = MutableLiveData()
    val searchMedias: MutableLiveData<List<Media?>> = MutableLiveData()
    val empty: LiveData<Boolean> = medias.map { it.isEmpty() }
    val emptySearch: LiveData<Boolean> = searchMedias.map { it.isEmpty() }

    // Save last search call to not do another (refresh doesn't work)
    private var lastSearch: String? = null

    private var page: Int = 0

    fun refresh() {
        page = 0
        dataLoading.value = false
        if (!lastSearch.isNullOrEmpty()) {
            searchByTitle(lastSearch!!)
        } else {
            getShows()
        }
    }

    // TODO: Add callback to the view
    fun getShows() {
        if (dataLoading.value != true) {
            if (page > 0) {
                medias.postValue((medias.value ?: mutableListOf()) + null)
            }
            launchRepositoryCall(
                mediaRepository.getMedias(page), onSuccess = {
                    page += 1
                    medias.postValue((medias.value ?: mutableListOf()).plus(it).filterNotNull()
                        .distinctBy { media -> media.id })
                    dataLoading.postValue(false)
                }
            )
        }
    }


    fun searchByTitle(title: String) {
        if (dataLoading.value != true && title != "") {
            launchRepositoryCall(mediaRepository.searchMediaByTitle(title), onSuccess = {
                searchMedias.postValue(it)
            })
        }
    }

    fun sortByAlphabetical() {
        medias.value?.sortByAlphabetical()?.let {
            medias.value = it
        }
    }

    fun sortByRating() {
        medias.value?.sortByRatingDescending()?.let {
            medias.value = it
        }
    }
}