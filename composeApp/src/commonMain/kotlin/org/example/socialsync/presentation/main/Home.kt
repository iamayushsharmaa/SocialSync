package org.example.socialsync.presentation.main

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Home(navController: NavHostController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        painter = painterResource(Resource.Icons.MENU_ICON),
                        contentDescription = "Menu",
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { /* Handle drawer click */ }
                    )
                },
                title = {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Resource.Icons.INSTAGRAM),
                            contentDescription = "connection icon",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape)
                                .padding(5.dp)
                        )
                        Text(
                            text = "My App",
                            modifier = Modifier,
                            textAlign = TextAlign.Center
                        )

                    }
                },
                actions = {
                    Text(
                        text = "Upgrade",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { /* Handle upgrade click */ },
                        color = Color.Black
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.White
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            Text(
                text = "Today",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            )



        }
    }
}