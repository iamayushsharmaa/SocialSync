package org.example.socialsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.example.socialsync.app.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initKoin()
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}
