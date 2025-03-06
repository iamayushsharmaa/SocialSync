package org.example.socialsync.presentation.bottomnav

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.Uri
import coil3.compose.LocalPlatformContext
import org.example.socialsync.presentation.main.AddPost
import org.example.socialsync.presentation.main.Draft
import org.example.socialsync.presentation.main.Home


@Composable
fun MainScreen(
    outerNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = innerNavController)
        }
    ) {  innerPadding->

        NavHost(
            navController = innerNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                Home(
                    navController = outerNavController
                )
            }
            composable(BottomNavItem.AddHabit.route) {
                AddPost(
                    navController = outerNavController,
                    onTagClick = {},
                )
            }
            composable(BottomNavItem.Draft.route) {
                Draft(
                    navController = outerNavController
                )
            }
        }
    }

}