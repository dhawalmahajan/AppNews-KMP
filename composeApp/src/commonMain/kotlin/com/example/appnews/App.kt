package com.example.appnews

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.example.appnews.ui.MainScreen
import com.example.appnews.utils.getRandomId

@Composable
@Preview
fun App() {
    MaterialTheme {
        val id = remember { getRandomId() }
        MainScreen()
    }
}