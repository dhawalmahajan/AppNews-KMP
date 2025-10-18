package com.example.appnews

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.logo
import com.example.appnews.di.initKoin
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "AppNews",
        state = WindowState(
            position = WindowPosition(Alignment.Center),
        ),
        icon = painterResource(Res.drawable.logo)
    ) {
//        window.minimumSize = Dimension(1200, 768)
        App()
    }
}