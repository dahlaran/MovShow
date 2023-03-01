package com.dahlaran.movshow.movies.data

import com.dahlaran.movshow.utils.data.DataState
import com.dahlaran.movshow.movies.models.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepositoryInterface {

    fun getMedias(page: Int): Flow<DataState<List<Media>>>
    fun searchMediaByTitle(title: String): Flow<DataState<List<Media>>>
    fun searchMediaById(id: String): Flow<DataState<Media>>

}