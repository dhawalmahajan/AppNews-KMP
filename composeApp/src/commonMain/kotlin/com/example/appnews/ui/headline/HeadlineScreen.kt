package com.example.appnews.ui.headline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.ic_browse
import appnews.composeapp.generated.resources.no_news
import com.example.appnews.data.repository.OnlineNewsRepository
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect
import org.jetbrains.compose.resources.stringResource

@Composable
fun HeadlineScreen(rootNavController: NavHostController) {
    val headlineViewModel = viewModel { HeadlineViewModel(OnlineNewsRepository()) }
    val uiState by headlineViewModel.newsStateFlow.collectAsState()
    uiState.DisplayResult(onIdle = {}, onLoading = {
        ShimmerEffect()
    }, onSuccess = { articleList ->
        if (articleList.isEmpty()) {
            EmptyContent(
                stringResource(Res.string.no_news),
                icon = Res.drawable.ic_browse,
                onRetryClick = {
                    headlineViewModel.getHeadline()
                }
            )
        } else {
            ArticleListScreen(articleList = articleList, navController = rootNavController)
        }


    }, onError = {
        EmptyContent(
            stringResource(Res.string.no_news),
            icon = Res.drawable.ic_browse,
            onRetryClick = {
                headlineViewModel.getHeadline()
            }
        )
    })

}