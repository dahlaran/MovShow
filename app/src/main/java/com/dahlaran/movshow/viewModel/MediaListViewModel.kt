package com.dahlaran.movshow.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.dahlaran.movshow.data.TVMazeRepository
import com.dahlaran.movshow.models.TVMazeMedia
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MediaListViewModel(application: Application) : AndroidViewModel(application) {
    private val mediaRepository = TVMazeRepository
    // Save Observables to remove when complete or viewModel is destroyed
    private val disposable = CompositeDisposable()
    val medias: MutableLiveData<List<TVMazeMedia>> = MutableLiveData()
    val dataLoading: MutableLiveData<Boolean> = MutableLiveData()
    val empty: LiveData<Boolean> = medias.map { it.isEmpty() }

    var lastSearch: String? = null

    fun refresh() {
        if (!lastSearch.isNullOrEmpty()) {
            searchByTitle(lastSearch!!)
        }
    }

    fun searchByTitle(title: String) {
        if (dataLoading.value != true && title != "") {

            dataLoading.value = true
            lastSearch = title
            disposable.add(mediaRepository.searchMediaByTitle(title).map { it }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { // onNext
                        medias.postValue(it)
                    },
                    { // onError
                        it.printStackTrace()
                    },
                    { // onComplete
                        dataLoading.postValue(false)
                    }
                ))
        }
    }

    override fun onCleared() {
        // Remove observable if viewModel is destroyed
        disposable.dispose()
        super.onCleared()
    }
}