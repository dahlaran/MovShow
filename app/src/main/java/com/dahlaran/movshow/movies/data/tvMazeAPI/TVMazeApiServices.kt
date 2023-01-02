package com.dahlaran.movshow.movies.data.tvMazeAPI

import com.dahlaran.movshow.movies.models.sendByApi.Show
import com.dahlaran.movshow.movies.models.sendByApi.TVMazeMedia
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVMazeApiServices {

    @GET("shows")
    suspend fun getMediaShows(@Query("page") page: Int): Response<List<Show>?>

    @GET("shows/{id}?embed=episodes")
    suspend fun searchMediaById(@Path("id") id: String): Response<Show>

    @GET("search/shows?embed=episodes")
    suspend fun searchMediaByTitle(@Query("q") title: String): Response<List<TVMazeMedia>?>
}