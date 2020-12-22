package com.dahlaran.movshow.data

import android.util.LruCache
import com.dahlaran.movshow.data.tvMazeAPI.TVMazeApiServices
import com.dahlaran.movshow.models.Show
import com.dahlaran.movshow.models.TVMazeMedia
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVMazeRepository @Inject constructor(private var service: TVMazeApiServices) : MediaRepositoryInterface{
    companion object {
        private const val MAX_ENTRIES: Int = 30
    }

    private val searchCache: LruCache<String, List<TVMazeMedia>> by lazy {
        val cache = LruCache<String, List<TVMazeMedia>>(MAX_ENTRIES)
        cache.put("", listOf())
        cache
    }

    override suspend fun getMedias(): Flowable<List<TVMazeMedia>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMediaByTitle(title: String): Observable<List<TVMazeMedia>> {
        return service.searchMediaByTitle(title).doOnNext { searchCache.put(title, it) }
    }

    override suspend fun searchMediaById(id: String): Observable<Show> {
        return service.searchMediaById(id)
    }
}