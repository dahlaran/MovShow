package com.dahlaran.movshow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.dahlaran.movshow.data.TVMazeRepository
import com.dahlaran.movshow.models.Media
import com.dahlaran.movshow.utils.sortByAlphabetical
import com.dahlaran.movshow.utils.sortByRatingDescending
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaListViewModel @Inject constructor(var mediaRepository: TVMazeRepository) : ViewModel() {

    // Save Observables to remove when complete or viewModel is destroyed
    private val disposable = CompositeDisposable()
    val medias: MutableLiveData<List<Media>> = MutableLiveData()
    val dataLoading: MutableLiveData<Boolean> = MutableLiveData()
    val empty: LiveData<Boolean> = medias.map { it.isEmpty() }

    // Save last search call to not do another (refresh doesn't work)
    private var lastSearch: String? = null
    // TODO: Put a system to make a call only 1 min after the last call (not have refresh spam but a refresh working)

    fun refresh() {
        if (!lastSearch.isNullOrEmpty()) {
            searchByTitle(lastSearch!!)
        } else {
            dataLoading.value = false
        }
    }

    fun searchByTitle(title: String) {
        if (dataLoading.value != true && title != "") {
            dataLoading.value = true
            lastSearch = title
            val coroutineScope = CoroutineScope(Dispatchers.Default + Job())

            coroutineScope.launch {
                disposable.add(mediaRepository.searchMediaByTitle(title)
                    .map {
                        it.map { tvMedia ->
                            Media.fromTVMazeMedia(tvMedia)
                        }
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { // onNext
                            medias.postValue(it)
                        },
                        { // onError
                            it.printStackTrace()
                            dataLoading.postValue(false)
                        },
                        { // onComplete
                            dataLoading.postValue(false)
                        }
                    ))
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