package com.example.appnews.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.appnews.navigation.Graph
import com.example.appnews.navigation.MainRouteScreens
import com.example.appnews.ui.bookmark.BookmarkScreen
import com.example.appnews.ui.headline.HeadlineScreen
import com.example.appnews.ui.search.SearchScreen


fun NavGraphBuilder.mainNavGraph(
    rootNavController: NavHostController,
    paddingValues: PaddingValues,
) {

    navigation(
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreens.Headline.route
    ) {
        composable(route = MainRouteScreens.Headline.route) {
            HeadlineScreen(rootNavController, paddingValues)
        }
        composable(route = MainRouteScreens.Search.route) {
            SearchScreen(rootNavController, paddingValues)
        }
        composable(route = MainRouteScreens.Bookmark.route) {
            BookmarkScreen(rootNavController, paddingValues)
        }

    }
}