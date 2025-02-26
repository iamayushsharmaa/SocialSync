package org.example.socialsync.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.socialsync.res.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignUp() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("")  }

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .statusBarsPadding()
                .background(Color.White),
        ){
            Spacer(Modifier.weight(1f))
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ){
                Text(
                    text = "Sign up",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Email",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 3.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,

                        ),
                    placeholder = { Text(
                        text = "Enter your email",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                    ) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .height(62.dp)
                        .padding(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray
                    )
                )
                Text(
                    text = "Create a password",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 3.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        ),
                    placeholder = { Text(
                        text = "repeat password",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                    ) },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(Res.Icons.EYE_OPEN),
                            contentDescription = "show password"
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .height(62.dp)
                        .padding(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray
                    )
                )
                Text(
                    text = "Confirm password",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 3.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,

                        ),
                    placeholder = { Text(
                        text = "repeat password",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                    ) },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(Res.Icons.EYE_OPEN),
                            contentDescription = "show password"
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .height(62.dp)
                        .padding(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray
                    )
                )

                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(vertical = 5.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.LightGray
                    ),
                ){
                    Text(
                        text = "Sign up",
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            val annotatedText = buildAnnotatedString {
                append("Already have an account? ")

                pushStringAnnotation(tag = "SIGN_IN", annotation = "sign_in")
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.SemiBold)) {
                    append("Sign in")
                }
                pop()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                    .padding(top = 7.dp, bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                ClickableText(
                    text = annotatedText,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "SIGN_IN", start = offset, end = offset)
                            .firstOrNull()?.let {
                                //navController.navigate("signin")
                            }
                    }
                )
            }

        }
    }
}
