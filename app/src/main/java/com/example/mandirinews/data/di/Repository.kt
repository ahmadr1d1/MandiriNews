package com.example.mandirinews.data.di

import com.example.mandirinews.data.response.NewsResponse
import com.example.mandirinews.data.retrofit.ApiService

class Repository(
    private val apiService: ApiService
) {

    suspend fun getHeadlineNews(): NewsResponse {
        return apiService.getHeadlineNews()
    }
    suspend fun getAllNews(query: String = "berita"): NewsResponse {
        return apiService.getAllNews(query)
    }

}