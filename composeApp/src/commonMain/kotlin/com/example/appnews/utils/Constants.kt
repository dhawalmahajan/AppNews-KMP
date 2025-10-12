package com.example.appnews.utils

import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.bookmark
import appnews.composeapp.generated.resources.headlines
import appnews.composeapp.generated.resources.ic_bookmark_outlined
import appnews.composeapp.generated.resources.ic_headline
import appnews.composeapp.generated.resources.ic_search
import appnews.composeapp.generated.resources.search
import com.example.appnews.navigation.BottomNavigationItem

enum class Type {
    Mobile, Desktop, Web
}

val bottomNavigationItemList = listOf(
    BottomNavigationItem(
        icon = Res.drawable.ic_headline,
        title = Res.string.headlines, route = "headlines"
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search, route = "search"
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_bookmark_outlined,
        title = Res.string.bookmark, route = "bookmarks"
    ),
)