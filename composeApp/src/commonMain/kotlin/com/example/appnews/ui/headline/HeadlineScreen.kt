package com.example.appnews.ui.headline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect

@Composable
fun HeadlineScreen(rootNavController: NavHostController) {
    val headlineViewModel = viewModel { HeadlineViewModel() }
    val uiState by headlineViewModel.newsStateFlow.collectAsState()
    uiState.DisplayResult(onIdle = {}, onLoading = {
        ShimmerEffect()
    }, onSuccess = { articleList ->
        if (articleList.isEmpty()) {
            EmptyContent("No Data")
        } else {
            ArticleListScreen(articleList = articleList, navController = rootNavController)
        }


    }, onError = {
        EmptyContent(it)
    })

}