package com.dahlaran.movshow.data

import com.dahlaran.movshow.models.Show
import com.dahlaran.movshow.models.TVMazeMedia
import io.reactivex.Flowable
import io.reactivex.Observable

interface MediaRepositoryInterface {

    suspend fun getMedias(): Flowable<List<TVMazeMedia>>
    suspend fun searchMediaByTitle(title: String): Observable<List<TVMazeMedia>>
    suspend fun searchMediaById(id: String): Observable<Show>

}