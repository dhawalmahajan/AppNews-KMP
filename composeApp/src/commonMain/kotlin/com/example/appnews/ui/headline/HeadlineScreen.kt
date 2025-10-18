package com.example.appnews.ui.headline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.ic_browse
import appnews.composeapp.generated.resources.no_news
import com.example.appnews.di.koinViewModel
import com.example.appnews.theme.xSmallPadding
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect
import com.example.appnews.utils.categoryList
import org.jetbrains.compose.resources.stringResource

@Composable
fun HeadlineScreen(rootNavController: NavHostController) {
    val headlineViewModel = koinViewModel<HeadlineViewModel>()
    val uiState by headlineViewModel.newsStateFlow.collectAsState()

    Column {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(xSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(
                xSmallPadding,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            items(categoryList, key = { it }) { category ->
                FilterChip(
                    selected = headlineViewModel.category == category,
                    onClick = {
                        headlineViewModel.category = category
                        headlineViewModel.getHeadline(headlineViewModel.category)
                    },
                    label = { Text(category) }
                )

            }
        }
        uiState.DisplayResult(onIdle = {}, onLoading = {
            ShimmerEffect()
        }, onSuccess = { articleList ->
            if (articleList.isEmpty()) {
                EmptyContent(
                    stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,
                    onRetryClick = {
                        headlineViewModel.getHeadline(headlineViewModel.category)
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
                    headlineViewModel.getHeadline(headlineViewModel.category)
                }
            )
        })
    }


}