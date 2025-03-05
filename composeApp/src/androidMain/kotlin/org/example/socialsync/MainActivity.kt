package org.example.socialsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.example.socialsync.app.App
import org.example.socialsync.di.initKoin
import org.example.socialsync.presentation.main.Home
import org.example.socialsync.presentation.main.component.ScheduledPostDesign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initKoin()
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
private fun HomePrev() {
    //Home(navController = rememberNavController())
    //ScheduledPostDesign()


}