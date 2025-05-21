package com.example.mandirinews.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mandirinews.data.di.Repository
import com.example.mandirinews.data.response.ArticlesItem
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _headlineNewsResult = MutableLiveData<List<ArticlesItem?>?>()
    val headlineNewsResult: MutableLiveData<List<ArticlesItem?>?> = _headlineNewsResult

    private val _allNewsResult = MutableLiveData<List<ArticlesItem?>?>()
    val allNewsResult: MutableLiveData<List<ArticlesItem?>?> = _allNewsResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

//    init {
//        getHeadlineNews()
//        getAllNews()
//    }

    fun getHeadlineNews() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val headlines = repository.getHeadlineNews()
                _headlineNewsResult.value = headlines.articles
            } catch (e: Exception) {
                _isError.value = true
                Log.e("MainViewModel", "Error fetching headline news: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAllNews() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val all = repository.getAllNews()
                _allNewsResult.value = all.articles
            } catch (e: Exception) {
                _isError.value = true
                Log.e("MainViewModel", "Error fetching all news: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}