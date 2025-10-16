package com.example.appnews.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.ic_network_error
import appnews.composeapp.generated.resources.no_news
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarkScreen(navController: NavController) {
    val bookmarkViewModel = viewModel { BookmarkViewModel() }
    val uiState by bookmarkViewModel.newsStateFlow.collectAsState()
    uiState.DisplayResult(onIdle = {}, onLoading = {
        ShimmerEffect()
    }, onSuccess = { articleList ->
        if (articleList.isEmpty()) {
            EmptyContent(
                stringResource(Res.string.no_news),
                icon = Res.drawable.ic_network_error,
                onRetryClick = {

                }
            )
        } else {
            ArticleListScreen(articleList = articleList, navController = navController)
        }


    }, onError = {
        EmptyContent(
            stringResource(Res.string.no_news),
            icon = Res.drawable.ic_network_error,
            onRetryClick = {

            }
        )
    })
}