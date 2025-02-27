package org.example.socialsync.presentation.socials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConnectButton(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    text: StringResource
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(shape = RoundedCornerShape(14.dp), color = Color.Black),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Icon(
            painter = painterResource(icon),
            tint = Color.White,
            contentDescription = "Socials icon"
        )
         Spacer(Modifier.width(20.dp))
        Text(
            text = stringResource(text),
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
        )
    }
}