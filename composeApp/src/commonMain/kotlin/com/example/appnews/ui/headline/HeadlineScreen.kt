package com.example.appnews.ui.headline

import androidx.compose.runtime.Composable
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.utils.articles

@Composable
fun HeadlineScreen() {
    ArticleListScreen(articleList = articles)
}