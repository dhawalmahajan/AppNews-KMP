package com.example.appnews

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.appnews.navigation.graphs.RootNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        RootNavGraph()
    }
}