package org.example.socialsync.presentation.socials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConnectDesign(modifier: Modifier = Modifier) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
            .background(shape = RoundedCornerShape(14.dp), color = Color.White),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
//        Icon(
//            painter = painterResource(),
//            contentDescription = "Socials icon"
//        )
         Spacer(Modifier.width(10.dp))
        Text(
            text = "Connect Instagram",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}