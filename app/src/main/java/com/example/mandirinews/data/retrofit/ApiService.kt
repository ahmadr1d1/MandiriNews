package com.example.mandirinews.data.retrofit

import com.example.mandirinews.data.response.NewsResponse
import retrofit2.http.*

interface ApiService {

    @GET("top-headlines?country=us")
    suspend fun getHeadlineNews(
        @Query("apiKey") apiKey: String = "11079e93c70941de95bef64724ec06f2"
    ): NewsResponse

    @GET("everything")
    suspend fun getAllNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = "11079e93c70941de95bef64724ec06f2"
    ): NewsResponse
}