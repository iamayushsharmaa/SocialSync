package org.example.socialsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.socialsync.app.App
import org.example.socialsync.authentication.SignIn
import org.example.socialsync.authentication.SignUp
import org.example.socialsync.presentation.socials.ConnectDesign
import org.example.socialsync.presentation.socials.ConnectSocials

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    ConnectDesign()
}