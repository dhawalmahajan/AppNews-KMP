package com.example.appnews

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
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