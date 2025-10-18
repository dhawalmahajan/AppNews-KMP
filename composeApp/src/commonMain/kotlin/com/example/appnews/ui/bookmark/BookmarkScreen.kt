package com.example.appnews.ui.bookmark

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.ic_network_error
import appnews.composeapp.generated.resources.no_news
import appnews.composeapp.generated.resources.setting
import com.example.appnews.di.koinViewModel
import com.example.appnews.navigation.SettingRouteScreen
import com.example.appnews.ui.common.ArticleListScreen
import com.example.appnews.ui.common.EmptyContent
import com.example.appnews.ui.common.ShimmerEffect
import com.example.appnews.utils.bottomNavigationItemList
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(navController: NavController, paddingValues: PaddingValues) {
    val bookmarkViewModel =
        koinViewModel<BookmarkViewModel>()
    val uiState by bookmarkViewModel.newsStateFlow.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        TopAppBar(
            title = {
                Text(
                    stringResource(bottomNavigationItemList[2].title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }, actions = {
                IconButton(
                    onClick = {
                        navController.navigate(SettingRouteScreen.Setting.route)
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
        uiState.DisplayResult(onIdle = {}, onLoading = {
            ShimmerEffect()
        }, onSuccess = { articleList ->
            if (articleList.isEmpty()) {
                EmptyContent(
                    stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_network_error,
                    isOnRetryBtnVisible = false
                )
            } else {
                ArticleListScreen(articleList = articleList, navController = navController)
            }


        }, onError = {
            EmptyContent(
                stringResource(Res.string.no_news),
                icon = Res.drawable.ic_network_error,
                onRetryClick = {
                    bookmarkViewModel.getHeadline()
                }
            )
        })
    }

}