package com.example.appnews.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.model.Article
import com.example.appnews.data.repository.LocalNewsRepository
import com.example.appnews.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {
    private val _newsStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow

    init {
        getHeadline()
    }

    fun getHeadline() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
                localNewsRepository.getArticles().catch {
                    it.printStackTrace()
                    _newsStateFlow.emit(Resource.Error(it.message.toString()))
                }.collect { articleList ->
                    _newsStateFlow.emit(Resource.Success(articleList))
                }
            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}