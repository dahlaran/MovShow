package com.dahlaran.movshow.data

import com.dahlaran.movshow.models.Show
import com.dahlaran.movshow.models.TVMazeMedia
import io.reactivex.Flowable
import io.reactivex.Observable

interface MediaRepositoryInterface {

    fun getMedias(): Flowable<List<TVMazeMedia>>
    fun searchMediaByTitle(title: String): Observable<List<TVMazeMedia>>
    fun searchMediaById(id: String): Observable<Show>

}