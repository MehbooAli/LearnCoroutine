package com.example.learncoroutine.Data.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val Base_Url = "https://jsonplaceholder.typicode.com/"

    val api: ApiService by lazy {
        val retrofit =  Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(
            GsonConverterFactory.create()).build()

        retrofit.create(ApiService::class.java)
    }
}