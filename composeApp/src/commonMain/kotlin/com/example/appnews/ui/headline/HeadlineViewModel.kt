package com.example.appnews.ui.headline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.model.Article
import com.example.appnews.data.model.ErrorResponse
import com.example.appnews.data.model.NewsResponse
import com.example.appnews.data.repository.OnlineNewsRepository
import com.example.appnews.utils.Resource
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeadlineViewModel(
    private val onlineNewsRepository: OnlineNewsRepository
) : ViewModel() {
    private val _newsStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow

    init {
        getHeadline()
    }

    private fun getHeadline() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
                val httpResponse = onlineNewsRepository.getNews()
                if (httpResponse.status.value in 200..299) {
                    val newsResponse =
                        httpResponse.body<NewsResponse>()
                    _newsStateFlow.emit(Resource.Success(newsResponse.articles))

                } else {
                    val newsResponse =
                        httpResponse.body<ErrorResponse>()
                    _newsStateFlow.emit(Resource.Error(newsResponse.message.toString()))
                }

            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}