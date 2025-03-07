package org.example.socialsync.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.socialsync.app.AppColor
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SocialsDesign(
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        verticalAlignment = CenterVertically,

    ){
        LazyRow (
            modifier = Modifier
                .padding(horizontal = 12.dp),
        ) {
            items(5){
                Icon(
                    painter = painterResource(Resource.Icons.GOOGLE),
                    contentDescription = "all social icons",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(horizontal = 3.dp)
                        .clip(CircleShape)
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(shape = CircleShape, color = AppColor.WhiteFade),
                    contentAlignment = Center
                ){
                    Text(
                        text = "+1",
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                    )
                }
            }

        }
    }
}