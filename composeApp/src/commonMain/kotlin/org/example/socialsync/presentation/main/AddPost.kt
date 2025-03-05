package org.example.socialsync.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.example.socialsync.app.AppColor
import org.example.socialsync.presentation.main.component.AttachmentRow
import org.example.socialsync.presentation.main.component.TextInput
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddPost(navController: NavHostController) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(AppColor.White)
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
                    .size(28.dp)
                    .padding(start = 12.dp)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(Resource.Icons.GOOGLE),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
            )
            Spacer(Modifier.weight(1f))
        }

        Spacer(Modifier.height(10.dp))
        TextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(Modifier.height(10.dp))

        AttachmentRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .background(shape = RoundedCornerShape(24.dp), color = AppColor.WhiteFade)
        )

    }
}