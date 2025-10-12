package com.example.appnews.utils

import androidx.compose.ui.unit.Dp
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.bookmark
import appnews.composeapp.generated.resources.dark_mode
import appnews.composeapp.generated.resources.headlines
import appnews.composeapp.generated.resources.ic_bookmark_outlined
import appnews.composeapp.generated.resources.ic_headline
import appnews.composeapp.generated.resources.ic_search
import appnews.composeapp.generated.resources.light_mode
import appnews.composeapp.generated.resources.search
import appnews.composeapp.generated.resources.system_default
import com.example.appnews.navigation.BottomNavigationItem
import com.example.appnews.navigation.MainRouteScreens
import org.jetbrains.compose.resources.StringResource

enum class Type {
    Mobile, Desktop, Web
}

val bottomNavigationItemList = listOf(
    BottomNavigationItem(
        icon = Res.drawable.ic_headline,
        title = Res.string.headlines, route = MainRouteScreens.Headline.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search, route = MainRouteScreens.Search.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_bookmark_outlined,
        title = Res.string.bookmark, route = MainRouteScreens.Bookmark.route
    ),
)

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}

data class Size(
    val width: Dp,
    val height: Dp
)
