package org.example.socialsync.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.socialsync.app.AppColor
import org.example.socialsync.res.Resource
import org.example.socialsync.res.Resource.Images.EGYPT_PHOTO
import org.jetbrains.compose.resources.painterResource

@Composable
fun ScheduledPostDesign() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .background(color = Color.White)
            .border(width = 1.dp,shape = RoundedCornerShape(24.dp), color = AppColor.BlackFade)
        ,
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(top = 25.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = CenterVertically,
        ) {
            Icon(
                painter = painterResource(Resource.Icons.TIME_ICON),
                contentDescription = "icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(26.dp)
            )
            Text(
                text = "12:00",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(Resource.Icons.NOTIFICATION_ICON),
                contentDescription = "icon",
                tint = Color.Red,
                modifier = Modifier
                    .size(26.dp)
            )
            Text(
                text = "Notification",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(Modifier.weight(1f))
        }

        Spacer(Modifier.height(16.dp))
        Text(
            text = "caption or tweet...",
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 6.dp)
        )
        Spacer(Modifier.height(14.dp))
        Image(
            painter = painterResource(Resource.Images.EGYPT_PHOTO),
            contentDescription = "posting image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(shape = RoundedCornerShape(24.dp),color = Color.Transparent)
                .padding(horizontal = 8.dp, vertical = 5.dp),
        )
    }
}