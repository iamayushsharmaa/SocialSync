package org.example.socialsync.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.socialsync.app.AppColor

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    maxWords: Int = 280, // Twitter-like word limit
    minHeight: Dp = 120.dp // Minimum height for the input
) {
    var text by remember { mutableStateOf("") }
    val words = text.split("\\s+".toRegex()).filter { it.isNotEmpty() }
    val wordCount = words.size
    val isOverLimit = wordCount > maxWords

    Column(
        modifier = modifier
            .background(AppColor.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF5F8FA),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isOverLimit) Color.Red else Color(0xFF657786),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            TextField(
                value = text,
                onValueChange = { newText ->
                    val newWords = newText.split("\\s+".toRegex()).filter { it.isNotEmpty() }
                    if (newWords.size <= maxWords || newText.length < text.length) {
                        text = newText
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = minHeight)
                    .align(Alignment.TopStart) // Align to top-start
                    .padding(6.dp),
                placeholder = {
                    Text(
                        text = "What's happening?",
                        fontSize = 16.sp,
                        color = Color(0xFF657786)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color(0xFF1DA1F2)
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Default
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, end = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "$wordCount/$maxWords",
                color = if (isOverLimit) Color.Red else Color(0xFF657786),
                fontSize = 12.sp
            )
        }
    }
}