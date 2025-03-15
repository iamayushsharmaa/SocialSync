package org.example.socialsync.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PostDetailScreen (
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { Text(text = "Post") },
                navigationIcon = {
                    Icon(
                        painter = painterResource(Resource.Icons.BACK_ARRAOW),
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navController.popBackStack() },
                        contentDescription = "back Icon"
                    )
                },
            )
        }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(it)
        ){
            Text(
                text = "Connections",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Text",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Caption...",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
//            Image(
//                painter = painterResource(),
//                contentDescription = "",
//
//            )
        }
    }
}
