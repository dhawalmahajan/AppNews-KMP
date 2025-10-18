package com.example.appnews

import androidx.compose.ui.window.ComposeUIViewController
import com.example.appnews.di.initKoin

fun MainViewController() = ComposeUIViewController(
    { initKoin() }
) { App() }