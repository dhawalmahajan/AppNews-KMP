package com.example.appnews.ui.articleDetail

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
    fun bookmarkArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.upsert(article)
        }
    }
}