package com.example.appnews

import androidx.compose.runtime.Composable
import com.example.appnews.navigation.graphs.RootNavGraph
import com.example.appnews.theme.NewsAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    NewsAppTheme(darkTheme = true) {
        RootNavGraph()
    }
}