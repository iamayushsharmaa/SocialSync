package org.example.socialsync.presentation.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AttachmentRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Row (
            modifier = Modifier,
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource( Resource.Icons.EMAIL), // Example icon (e.g., image attachment)
                contentDescription = "Image",
                tint = Color(0xFF657786), // Twitter-like gray
                modifier = Modifier.size(24.dp) // Icon size
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Attach",
                fontSize = 12.sp,
                color = Color(0xFF657786),
                modifier = Modifier.padding(top = 4.dp) // Space between icon and text
            )
        }

        Row (
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource(Resource.Icons.GOOGLE), // Example icon (e.g., file attachment)
                contentDescription = "File",
                tint = Color(0xFF657786),
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(8.dp))

            Text(
                text = "File",
                fontSize = 12.sp,
                color = Color(0xFF657786),
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Row(
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource(Resource.Icons.BACK_ICON),
                contentDescription = "Location",
                tint = Color(0xFF657786),
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(8.dp))

            Text(
                text = "Tag",
                fontSize = 12.sp,
                color = Color(0xFF657786),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}