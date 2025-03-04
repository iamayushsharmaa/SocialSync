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
import org.example.socialsync.presentation.main.Analysis
import org.example.socialsync.presentation.main.Draft
import org.example.socialsync.presentation.main.Home
import org.example.socialsync.presentation.main.Setting


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
                    navController = outerNavController
                )
            }
            composable(BottomNavItem.Draft.route) {
                Draft(navController = outerNavController)
            }
            composable(BottomNavItem.Analysis.route) {
                Analysis(navController = outerNavController)
            }
            composable(BottomNavItem.Settings.route) {
                Setting(
                    navController = innerNavController
                )
            }
        }
    }

}