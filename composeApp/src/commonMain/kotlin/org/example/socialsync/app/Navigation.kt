package org.example.socialsync.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.socialsync.authentication.OauthSignIn
import org.example.socialsync.authentication.SignIn
import org.example.socialsync.authentication.SignUp
import org.example.socialsync.presentation.socials.ConnectSocials
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import socialsync.composeapp.generated.resources.Res
import socialsync.composeapp.generated.resources.connect_social
import socialsync.composeapp.generated.resources.home
import socialsync.composeapp.generated.resources.o_auth
import socialsync.composeapp.generated.resources.sign_in
import socialsync.composeapp.generated.resources.sign_up


enum class SocialScreen(val title: StringResource) {
    OauthSignIn(title = Res.string.o_auth),
    SignUp(title = Res.string.sign_up),
    SignIn(title = Res.string.sign_in),
    Home(title = Res.string.home),
    ConnectSocial(title = Res.string.connect_social)
}

@Composable
@Preview
fun SocialApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = SocialScreen.valueOf(
        backStackEntry?.destination?.route ?: SocialScreen.SignUp.name
    )

    Scaffold() { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = SocialScreen.SignUp.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = SocialScreen.OauthSignIn.name){
                OauthSignIn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    navController = navController,
                    onNavigateToSignIn = {
                        navController.navigate(SocialScreen.SignIn.name)
                    },

                    onGoogleClick = { },
                    onXClick = { },
                    onEmailClick = {
                        navController.navigate(SocialScreen.SignUp.name)
                    }
                )
            }

            composable(route = SocialScreen.SignUp.name){
                SignUp(
                    navController = navController,
                    onNavigateToSignIn = {
                        navController.navigate(SocialScreen.SignIn.name)
                    },
                    onNavigateToHome = {
                        navController.navigate(SocialScreen.Home.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = SocialScreen.SignIn.name){
                SignIn(
                    navController = navController,
                    onNavigateToSignUp = {
                        navController.navigate(SocialScreen.SignUp.name)
                    },
                    onNavigateToHome = {
                        navController.navigate(SocialScreen.Home.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = SocialScreen.ConnectSocial.name){
                ConnectSocials(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = SocialScreen.Home.name){
//                Home(
//                    navController = navController,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp)
//                )
            }
        }
    }

}