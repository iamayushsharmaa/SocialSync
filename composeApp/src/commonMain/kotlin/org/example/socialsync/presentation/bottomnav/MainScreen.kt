package org.example.socialsync.presentation.bottomnav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.socialsync.presentation.main.AddPost
import org.example.socialsync.presentation.main.Home


@Composable
fun MainScreen(outerNavController: NavHostController) {
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
                Home(navController = outerNavController)
            }
            composable(BottomNavItem.AddHabit.route) {
                AddPost(
                    navController = innerNavController
                )
            }
        }
    }

}