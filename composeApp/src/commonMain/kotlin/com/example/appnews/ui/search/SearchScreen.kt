package com.example.appnews.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.ic_browse
import appnews.composeapp.generated.resources.no_news
import appnews.composeapp.generated.resources.setting
import appnews.composeapp.generated.resources.type_to_search
import com.example.appnews.di.koinViewModel
import com.example.appnews.navigation.SettingRouteScreen
import com.example.appnews.theme.xSmallPadding
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect
import com.example.appnews.ui.search.components.SearchBarScreen
import com.example.appnews.utils.bottomNavigationItemList
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(rootNavController: NavHostController, paddingValues: PaddingValues) {
    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }
    val searchViewModel = koinViewModel<SearchViewModel>()
    val uiState by searchViewModel.newsStateFlow.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(xSmallPadding)
    ) {
        TopAppBar(
            title = {
                Text(
                    stringResource(bottomNavigationItemList[1].title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }, actions = {
                IconButton(
                    onClick = {
                        rootNavController.navigate(SettingRouteScreen.Setting.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings, contentDescription = stringResource(
                            Res.string.setting
                        )
                    )

                }

            }
        )
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