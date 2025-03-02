package org.example.socialsync.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.core.context.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext{
            SocialApp()
        }
    }
}
