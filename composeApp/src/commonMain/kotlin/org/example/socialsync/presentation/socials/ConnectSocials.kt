package org.example.socialsync.presentation.socials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.example.socialsync.app.AppColor
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import socialsync.composeapp.generated.resources.Res
import socialsync.composeapp.generated.resources.connect_instagram
import socialsync.composeapp.generated.resources.connect_x
import socialsync.composeapp.generated.resources.connect_your_socials
import socialsync.composeapp.generated.resources.take_control_social_media

@Composable
fun ConnectSocials(
    navController: NavHostController,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Spacer(Modifier.width(14.dp))
            IconButton(
                modifier = Modifier
                    .size(36.dp)
                    .padding(top = 8.dp)
                    .background(shape = RoundedCornerShape(12.dp), color = Color.White),
                onClick = { }
            ){
                Icon(
                    painter = painterResource(Resource.Icons.BACK_ARRAOW),
                    tint = Color.Black,
                    contentDescription = "show password"
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(Res.string.connect_your_socials),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            color = Color.Black
        )
        Text(
            text = stringResource(Res.string.take_control_social_media),
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(30.dp))

        ConnectButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(horizontal = 16.dp)
                .background(shape = RoundedCornerShape(12.dp), color = AppColor.BlackFade),
            icon = Resource.Icons.INSTAGRAM,
            text = Res.string.connect_instagram,
            onClick = {
                onClick()
            }
        )
        Spacer(Modifier.height(12.dp))
        ConnectButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(horizontal = 16.dp)
                .background(shape = RoundedCornerShape(12.dp), color = AppColor.BlackFade),
            icon = Resource.Icons.X,
            text = Res.string.connect_x,
            onClick = {
                onClick()
            }
        )
    }
}