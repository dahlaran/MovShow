package com.dahlaran.movshow.movies.data

import android.util.LruCache
import com.dahlaran.movshow.data.DataState
import com.dahlaran.movshow.data.Error
import com.dahlaran.movshow.movies.data.tvMazeAPI.TVMazeApiServices
import com.dahlaran.movshow.movies.models.Media
import com.dahlaran.movshow.movies.models.sendByApi.Show
import com.dahlaran.movshow.movies.models.sendByApi.TVMazeMedia
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVMazeRepository @Inject constructor(private var service: TVMazeApiServices) :
    MediaRepositoryInterface {
    companion object {
        private const val MAX_ENTRIES: Int = 30
    }

    private val searchCache: LruCache<String, List<TVMazeMedia>> by lazy {
        val cache = LruCache<String, List<TVMazeMedia>>(MAX_ENTRIES)
        cache.put("", listOf())
        cache
    }

    override suspend fun getMedias(page: Int): Flow<DataState<List<Media>>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = service.getMediaShows(page)

                if (response.body() != null) {
                    emit(DataState.Success(response.body()!!.map {item -> Media.fromTVMazeShow(item) }))
                } else {
                    emit(DataState.Error(Error.fromString(response.errorBody().toString())))
                }
            } catch (e: Exception) {
                emit(DataState.Error(Error.fromException(e)))
            }
        }
    }

    override suspend fun searchMediaByTitle(title: String): Flow<DataState<List<Media>>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = service.searchMediaByTitle(title)

                if (response.body() != null) {
                    emit(DataState.Success(response.body()!!.map { item -> Media.fromTVMazeMedia(item) }))
                } else {
                    emit(DataState.Error(Error.fromString(response.errorBody().toString())))
                }
            } catch (e: Exception) {
                emit(DataState.Error(Error.fromException(e)))
            }
        }
    }

    override suspend fun searchMediaById(id: String): Flow<DataState<Media>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = service.searchMediaById(id)

                if (response.body() != null) {
                    emit(DataState.Success(Media.fromTVMazeShow(response.body()!!)))
                } else {
                    emit(DataState.Error(Error.fromString(response.errorBody().toString())))
                }
            } catch (e: Exception) {
                emit(DataState.Error(Error.fromException(e)))
            }
        }
    }
}