package com.dahlaran.movshow.movies.data

import com.dahlaran.movshow.data.DataState
import com.dahlaran.movshow.movies.models.Media
import com.dahlaran.movshow.movies.models.sendByApi.Show
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface MediaRepositoryInterface {

    suspend fun getMedias(page: Int): Flow<DataState<List<Media>>>
    suspend fun searchMediaByTitle(title: String): Flow<DataState<List<Media>>>
    suspend fun searchMediaById(id: String): Flow<DataState<Media>>

}