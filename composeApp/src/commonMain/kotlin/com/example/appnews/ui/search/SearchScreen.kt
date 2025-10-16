package com.example.appnews.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.ic_browse
import appnews.composeapp.generated.resources.no_news
import appnews.composeapp.generated.resources.type_to_search
import com.example.appnews.data.repository.OnlineNewsRepository
import com.example.appnews.theme.mediumPadding
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect
import com.example.appnews.ui.search.components.SearchBarScreen
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchScreen(rootNavController: NavHostController) {
    var searchQuery by rememberSaveable() {
        mutableStateOf("")
    }
    val searchViewModel = viewModel { SearchViewModel(OnlineNewsRepository()) }
    val uiState by searchViewModel.newsStateFlow.collectAsState()
    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        SearchBarScreen(
            text = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            onSearch = { query ->
                if (query.trim().isNotEmpty()) {
                    searchViewModel.searchQueryResult(query)
                }

            }

        )

        uiState.DisplayResult(onIdle = {
            EmptyContent(
                stringResource(Res.string.type_to_search),
                icon = Res.drawable.ic_browse,
                isOnRetryBtnVisible = false,

                )
        }, onLoading = {
            ShimmerEffect()
        }, onSuccess = { articleList ->
            if (articleList.isEmpty()) {
                EmptyContent(
                    stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,
                    onRetryClick = {
                        if (searchQuery.trim().isNotEmpty()) {
                            searchViewModel.searchQueryResult(searchQuery)
                        }
                    }
                )
            } else {
                ArticleListScreen(articleList = articleList, rootNavController)
            }
        }, onError = {
            EmptyContent(
                it,
                icon = Res.drawable.ic_browse,
                onRetryClick = {
                    if (searchQuery.trim().isNotEmpty()) {
                        searchViewModel.searchQueryResult(searchQuery)
                    }
                }
            )
        })
    }
}