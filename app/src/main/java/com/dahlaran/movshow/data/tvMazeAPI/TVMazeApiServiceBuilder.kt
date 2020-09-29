package com.dahlaran.movshow.data.tvMazeAPI

import com.dahlaran.movshow.data.HttpClientBuilder
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object TVMazeApiServiceBuilder {
    private const val API_URL = "https://api.tvmaze.com/"
    private val apiInterface : TVMazeApiServices
    init {
        val gsonBuilder = GsonBuilder()
        val client  = HttpClientBuilder.create()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()

        apiInterface = retrofit.create(TVMazeApiServices::class.java)
    }

    fun buildService(): TVMazeApiServices {
        return apiInterface
    }
}