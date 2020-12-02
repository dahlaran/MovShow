package com.dahlaran.movshow.data.tvMazeAPI

import com.dahlaran.movshow.models.Show
import com.dahlaran.movshow.models.TVMazeMedia
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVMazeApiServices {
    @GET("shows/{id}?embed=episodes")
    fun searchMediaById(@Path("id") id: String): Observable<Show>

    @GET("search/shows")
    fun searchMediaByTitle(@Query("q") title: String): Observable<List<TVMazeMedia>>
}