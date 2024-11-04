package com.example.learncoroutine.Data.Api

import com.example.learncoroutine.Data.Model.PostModel
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPost(): List<PostModel>
}