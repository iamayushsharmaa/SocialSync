package org.example.socialsync.presentation.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.socialsync.app.AppColor
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AttachmentRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Row (
            modifier = Modifier,
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource( Resource.Icons.EMAIL),
                contentDescription = "Image",
                tint = AppColor.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "Attach",
                fontSize = 12.sp,
                color = AppColor.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Row (
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource(Resource.Icons.GOOGLE),
                contentDescription = "File",
                tint = AppColor.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(6.dp))

            Text(
                text = "File",
                fontSize = 12.sp,
                color = AppColor.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Row(
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource(Resource.Icons.BACK_ICON),
                contentDescription = "Location",
                tint = AppColor.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "Tag",
                fontSize = 12.sp,
                color = AppColor.Black,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}