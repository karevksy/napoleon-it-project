package com.karevsky.napoleonit.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

val albumApi: AlbumsApi = Retrofit.Builder()
    .baseUrl("https://api.deezer.com/")
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()
    .create()