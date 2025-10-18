package com.example.appnews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.appnews.di.koinViewModel
import com.example.appnews.theme.NewsAppTheme
import com.example.appnews.ui.MainScreen
import com.example.appnews.ui.settings.SettingsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val settingsViewModel = koinViewModel<SettingsViewModel>()

    val currentTheme by settingsViewModel.currentTheme.collectAsState()
    NewsAppTheme(currentTheme) {
        MainScreen(settingsViewModel)
    }
}