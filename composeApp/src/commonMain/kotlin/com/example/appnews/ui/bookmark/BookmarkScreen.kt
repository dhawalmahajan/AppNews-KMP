package com.example.appnews.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect

@Composable
fun BookmarkScreen(navController: NavController) {
    val bookmarkViewModel = viewModel { BookmarkViewModel() }
    val uiState by bookmarkViewModel.newsStateFlow.collectAsState()
    uiState.DisplayResult(onIdle = {}, onLoading = {
        ShimmerEffect()
    }, onSuccess = { articleList ->
        if (articleList.isEmpty()) {
            EmptyContent("No Data")
        } else {
            ArticleListScreen(articleList = articleList, navController = navController)
        }


    }, onError = {
        EmptyContent(it)
    })
}