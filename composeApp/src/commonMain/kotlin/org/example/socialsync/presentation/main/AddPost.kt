package org.example.socialsync.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddPost(navController: NavHostController) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = CenterVertically
        ){
            Icon(
                painter = painterResource(Resource.Icons.BACK_ICON),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .weight(1f)
            )

            Icon(
                painter = painterResource(Resource.Icons.GOOGLE),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .weight(1f)
            )
        }
    }
}