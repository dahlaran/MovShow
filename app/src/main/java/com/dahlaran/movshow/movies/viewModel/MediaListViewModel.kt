package com.dahlaran.movshow.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.dahlaran.movshow.data.DataState
import com.dahlaran.movshow.movies.data.TVMazeRepository
import com.dahlaran.movshow.movies.models.Media
import com.dahlaran.movshow.movies.utils.sortByAlphabetical
import com.dahlaran.movshow.movies.utils.sortByRatingDescending
import com.dahlaran.movshow.utils.launchRepositoryCall
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MediaListViewModel @Inject constructor(var mediaRepository: TVMazeRepository) : ViewModel() {

    // Save Observables to remove when complete or viewModel is destroyed
    private val disposable = CompositeDisposable()
    val medias: MutableLiveData<List<Media?>> = MutableLiveData()
    val searchMedias: MutableLiveData<List<Media?>> = MutableLiveData()
    val dataLoading: MutableLiveData<Boolean> = MutableLiveData()
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
            launchRepositoryCall {
                mediaRepository.getMedias(page).onEach { dataState ->
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
                            page += 1
                            medias.postValue((medias.value ?: mutableListOf()).plus(dataState.data).filterNotNull()
                                .distinctBy { media -> media.id })
                            dataLoading.postValue(false)
                        }
                    }
                }.launchIn(this)
            }
        }
    }


    fun searchByTitle(title: String) {
        if (dataLoading.value != true && title != "") {
            launchRepositoryCall {
                mediaRepository.searchMediaByTitle(title).onEach { dataState ->
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
                            searchMedias.postValue(dataState.data)
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

    fun sortByAlphabetical() {
        val listSorted = medias.value?.sortByAlphabetical()
        medias.value = listSorted
    }

    fun sortByRating() {
        val listSorted = medias.value?.sortByRatingDescending()
        if (listSorted != null) {
            medias.value = listSorted
        }
    }
}