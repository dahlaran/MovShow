package com.dahlaran.movshow.data

import android.util.LruCache
import com.dahlaran.movshow.data.tvMazeAPI.TVMazeApiServiceBuilder
import com.dahlaran.movshow.models.Show
import com.dahlaran.movshow.models.TVMazeMedia
import io.reactivex.Flowable
import io.reactivex.Observable

object TVMazeRepository : MediaRepositoryInterface {
    private const val MAX_ENTRIES: Int = 20
    private val service = TVMazeApiServiceBuilder.buildService()

    private val searchCache: LruCache<String, List<TVMazeMedia>> by lazy {
        val cache = LruCache<String, List<TVMazeMedia>>(MAX_ENTRIES)
        cache.put("",listOf())
        cache
    }
    override fun getMedias(): Flowable<List<TVMazeMedia>> {
        TODO("Not yet implemented")
    }


    override fun searchMediaByTitle(title: String): Observable<List<TVMazeMedia>> {
        return service.searchMediaByTitle(title).doOnNext {  searchCache.put(title, it) }
    }

    override fun searchMediaById(id: String): Observable<Show> {
        return service.searchMediaById(id)
    }


}