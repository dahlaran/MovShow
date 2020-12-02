package com.dahlaran.movshow.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dahlaran.movshow.data.TVMazeRepository
import com.dahlaran.movshow.models.Media
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MediaDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val mediaRepository = TVMazeRepository
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
            dataLoading.value = true
            disposable.add(mediaRepository.searchMediaById(mediaId.toString())
                .map {
                    Media.fromTVMazeShow(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { // onNext
                        media.postValue(it)
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

    override fun onCleared() {
        // Remove observable if viewModel is destroyed
        disposable.dispose()
        super.onCleared()
    }
}