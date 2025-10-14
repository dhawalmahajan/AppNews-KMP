package com.example.appnews.ui.headline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect

@Composable
fun HeadlineScreen() {
    val headlineViewModel = viewModel { HeadlineViewModel() }
    val uiState by headlineViewModel.newsStateFlow.collectAsState()
    uiState.DisplayResult(onIdle = {}, onLoading = {
        ShimmerEffect()
    }, onSuccess = {
        ArticleListScreen(articleList = it)
    }, onError = {
        EmptyContent(it)
    })

}