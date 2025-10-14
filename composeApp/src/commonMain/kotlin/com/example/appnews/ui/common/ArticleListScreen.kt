package com.example.appnews.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.appnews.data.model.Article
import com.example.appnews.theme.xLargePadding
import com.example.appnews.utils.Type
import com.example.appnews.utils.getRandomId
import com.example.appnews.utils.getType

@Composable
fun ArticleListScreen(articleList: List<Article>) {
    val isDesktop = remember {
        getType() == Type.Desktop
    }
    LazyVerticalGrid(
        GridCells.Fixed(if (isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding)
    ) {
        items(articleList, key = {
            it.publishedAt + getRandomId()
        }) { article ->
            ArticleItem(article = article, onItemClick = {})
        }
    }
}