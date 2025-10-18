package com.example.appnews.ui.articleDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appnews.data.model.Article
import com.example.appnews.data.repository.LocalNewsRepository

class ArticleDetailViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {
    var isBookmarked: Boolean by mutableStateOf(false)

    suspend fun isArticleBookmarked(currentArticle: Article) {

        isBookmarked = localNewsRepository.getArticle(currentArticle.publishedAt) != null
    }

    suspend fun bookmarkArticle(currentArticle: Article) {
        if (!isBookmarked) {
            localNewsRepository.upsert(currentArticle)
        } else {
            localNewsRepository.delete(currentArticle)
        }
        isBookmarked = !isBookmarked
    }


}