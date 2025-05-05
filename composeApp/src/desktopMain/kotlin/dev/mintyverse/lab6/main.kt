package dev.mintyverse.lab6

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Lab6_MultiplatformTaskManager",
    ) {
        App()
    }
}