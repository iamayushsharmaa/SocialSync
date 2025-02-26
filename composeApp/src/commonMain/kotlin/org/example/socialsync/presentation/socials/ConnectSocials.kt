package org.example.socialsync.presentation.socials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.example.socialsync.res.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun ConnectSocials(navController: NavHostController, modifier: Modifier) {
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .statusBarsPadding()
                .background(Color.White),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
            ) {
                IconButton(
                    modifier = Modifier
                        .size(36.dp),
                    onClick = { }
                ){
                    Icon(
                        painter = painterResource(Res.Icons.EYE_OPEN),
                        contentDescription = "show password"
                    )
                }
            }
        }
    }
}