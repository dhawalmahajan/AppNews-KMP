package com.example.appnews.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.appnews.data.model.Article
import com.example.appnews.navigation.NewsRouteScreen
import com.example.appnews.theme.cardMinSize
import com.example.appnews.theme.mediumPadding
import com.example.appnews.utils.getRandomId
import kotlinx.serialization.json.Json

@Composable
fun ArticleListScreen(articleList: List<Article>, navController: NavController) {

    LazyVerticalStaggeredGrid(
        columns =
            StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,

        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding)
    ) {
        items(articleList, key = {
            it.publishedAt + getRandomId()
        }) { article ->
            ArticleItem(article = article, onItemClick = {
                val articleStr = Json.encodeToString(article)
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("article", articleStr)
                }
                navController.navigate(
                    NewsRouteScreen.newsDetail.route
                )
            })
        }
    }
}