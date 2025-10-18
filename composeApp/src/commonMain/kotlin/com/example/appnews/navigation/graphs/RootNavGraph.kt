package com.example.appnews.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appnews.data.model.Article
import com.example.appnews.navigation.Graph
import com.example.appnews.navigation.NewsRouteScreen
import com.example.appnews.navigation.SettingRouteScreen
import com.example.appnews.ui.articleDetail.ArticleDetailScreen
import com.example.appnews.ui.settings.SettingScreen
import com.example.appnews.ui.settings.SettingsViewModel
import kotlinx.serialization.json.Json

@Composable
fun RootNavGraph(
    rootNavController: NavHostController,
    innerPaddingValues: PaddingValues,
    settingsViewModel: SettingsViewModel
) {

    NavHost(
        navController = rootNavController,
        route = Graph.RootScreenGraph,
        startDestination = Graph.MainScreenGraph
    ) {
        mainNavGraph(rootNavController, innerPaddingValues)
        composable(route = SettingRouteScreen.Setting.route) {
            SettingScreen(rootNavController, settingsViewModel = settingsViewModel)
        }
        composable(route = NewsRouteScreen.newsDetail.route) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")
                ?.let {
                    val article = Json.decodeFromString<Article>(it)
                    ArticleDetailScreen(rootNavController, article = article)
                }

        }
    }

}