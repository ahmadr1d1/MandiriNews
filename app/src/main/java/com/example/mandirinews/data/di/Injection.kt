package com.example.mandirinews.data.di

import com.example.mandirinews.data.retrofit.ApiConfig
import com.example.mandirinews.data.retrofit.ApiService

object Injection {
    private fun provideNewsApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    fun provideNewsRepository(): Repository {
        val apiService = provideNewsApiService()
        return Repository(apiService)
    }
}