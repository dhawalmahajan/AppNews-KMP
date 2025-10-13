package com.example.appnews.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnews.navigation.Graph
import com.example.appnews.navigation.SettingRouteScreen
import com.example.appnews.ui.MainScreen
import com.example.appnews.ui.settings.SettingScreen

@Composable
fun RootNavGraph() {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.RootScreenGraph,
        startDestination = Graph.MainScreenGraph
    ) {
        composable(route = Graph.MainScreenGraph) {
            MainScreen(rootNavController)
        }
        composable(route = SettingRouteScreen.Setting.route) {
            SettingScreen(rootNavController)
        }
    }

}