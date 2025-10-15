package com.example.appnews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appnews.navigation.graphs.RootNavGraph
import com.example.appnews.theme.NewsAppTheme
import com.example.appnews.ui.settings.SettingsViewModel
import com.example.appnews.utils.AppPreference
import com.example.appnews.utils.dataStorePreference
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val appPreference = remember {
        AppPreference(dataStore = dataStorePreference())
    }
    val settingsViewModel = viewModel {
        SettingsViewModel(appPreference)
    }

    val currentTheme by settingsViewModel.currentTheme.collectAsState()
    NewsAppTheme(currentTheme) {
        RootNavGraph(settingsViewModel = settingsViewModel)
    }
}