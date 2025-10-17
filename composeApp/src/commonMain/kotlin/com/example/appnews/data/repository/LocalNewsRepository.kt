package com.example.appnews.data.repository

import com.example.appnews.data.database.NewsDao
import com.example.appnews.data.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn

class LocalNewsRepository(private val newsDao: NewsDao) {
    suspend fun upsert(article: Article) {
        newsDao.upsert(article)
    }

    fun getArticles() = newsDao.getArticles().flowOn(Dispatchers.IO)
}