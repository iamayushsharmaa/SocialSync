package org.example.socialsync

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.socialsync.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SocialSync",
    ) {
        App()
    }
}