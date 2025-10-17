package com.example.appnews.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnews.data.database.NewsDao
import com.example.appnews.data.model.Article
import com.example.appnews.navigation.Graph
import com.example.appnews.navigation.NewsRouteScreen
import com.example.appnews.navigation.SettingRouteScreen
import com.example.appnews.ui.MainScreen
import com.example.appnews.ui.articleDetail.ArticleDetailScreen
import com.example.appnews.ui.settings.SettingScreen
import com.example.appnews.ui.settings.SettingsViewModel
import kotlinx.serialization.json.Json

@Composable
fun RootNavGraph(settingsViewModel: SettingsViewModel, newsDao: NewsDao) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.RootScreenGraph,
        startDestination = Graph.MainScreenGraph
    ) {
        composable(route = Graph.MainScreenGraph) {
            MainScreen(rootNavController, newsDao)
        }
        composable(route = SettingRouteScreen.Setting.route) {
            SettingScreen(rootNavController, settingsViewModel = settingsViewModel)
        }
        composable(route = NewsRouteScreen.newsDetail.route) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")
                ?.let {
                    val article = Json.decodeFromString<Article>(it)
                    ArticleDetailScreen(rootNavController, article = article, newsDao)
                }

        }
    }

}