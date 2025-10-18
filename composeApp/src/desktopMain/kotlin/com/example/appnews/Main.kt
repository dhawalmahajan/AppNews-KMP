package com.example.appnews

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.example.appnews.di.initKoin
import java.awt.Dimension

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "AppNews",
        state = WindowState(
            position = WindowPosition(Alignment.Center),
        )
    ) {
        window.minimumSize = Dimension(1200, 768)
        App()
    }
}