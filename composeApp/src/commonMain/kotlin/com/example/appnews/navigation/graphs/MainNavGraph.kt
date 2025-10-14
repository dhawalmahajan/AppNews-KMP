package com.example.appnews.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appnews.navigation.Graph
import com.example.appnews.navigation.MainRouteScreens
import com.example.appnews.ui.bookmark.BookmarkScreen
import com.example.appnews.ui.headline.HeadlineScreen
import com.example.appnews.ui.search.SearchScreen

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreens.Headline.route
    ) {
        composable(route = MainRouteScreens.Headline.route) {
            HeadlineScreen(rootNavController)
        }
        composable(route = MainRouteScreens.Search.route) {
            SearchScreen(rootNavController)
        }
        composable(route = MainRouteScreens.Bookmark.route) {
            BookmarkScreen(rootNavController)
        }

    }
}