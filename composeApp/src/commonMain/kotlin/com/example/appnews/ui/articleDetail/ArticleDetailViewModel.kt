package com.example.appnews.ui.articleDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.database.NewsDao
import com.example.appnews.data.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val localNewsRepository: NewsDao
) : ViewModel() {
    var isBookmarked: Boolean by mutableStateOf(false)

    fun isArticleBookmarked(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.getArticle(currentArticle.publishedAt)?.let {
                isBookmarked = true
            }
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