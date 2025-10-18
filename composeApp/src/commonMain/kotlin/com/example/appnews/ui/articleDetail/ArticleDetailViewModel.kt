package com.example.appnews.ui.articleDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.model.Article
import com.example.appnews.data.repository.LocalNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {
    var isBookmarked: Boolean by mutableStateOf(false)

    fun isArticleBookmarked(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            isBookmarked = localNewsRepository.getArticle(currentArticle.publishedAt) != null
        }

    }

    fun bookmarkArticle(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isBookmarked) {
                localNewsRepository.upsert(currentArticle)
            } else {
                localNewsRepository.delete(currentArticle)
            }
            isBookmarked = !isBookmarked
        }

    }


}