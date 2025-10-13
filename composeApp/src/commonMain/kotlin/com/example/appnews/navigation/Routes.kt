package com.example.appnews.navigation

object Graph {
    const val RootScreenGraph = "rootScreenGraph"
    const val MainScreenGraph = "mainScreenGraph"

}

sealed class MainRouteScreens(val route: String) {
    object Headline : MainRouteScreens("headline")
    object Search : MainRouteScreens("search")
    object Bookmark : MainRouteScreens("bookmark")
}

sealed class SettingRouteScreen(val route: String) {
    object Setting : SettingRouteScreen("setting")
}