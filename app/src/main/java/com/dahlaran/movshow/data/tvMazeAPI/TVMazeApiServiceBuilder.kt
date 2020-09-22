package com.dahlaran.movshow.data.tvMazeAPI

import com.dahlaran.movshow.data.HttpClientBuilder
import com.dahlaran.movshow.utilis.Constant
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object TVMazeApiServiceBuilder {
    //private val client by lazy { OkHttpClient.Builder().build() }
    private const val API_URL = " http://api.tvmaze.com/"
    /*private val retrofit by lazy {Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
        .create(TVMazeApiServices::class.java)}*/
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