package com.example.animes.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL =
    "https://ghibliapi.herokuapp.com/"

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AnimeApiService {
    @GET("films")
    suspend fun getAnimes(): List<Anime>
}

object AnimeApi{
    val retrofitService: AnimeApiService by lazy {
        retrofit.create(AnimeApiService::class.java)
    }
}

