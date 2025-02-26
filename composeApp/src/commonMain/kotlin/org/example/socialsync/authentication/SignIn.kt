package org.example.socialsync.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import socialsync.composeapp.generated.resources.Res
import socialsync.composeapp.generated.resources.create_password
import socialsync.composeapp.generated.resources.dont_have_account
import socialsync.composeapp.generated.resources.email_address
import socialsync.composeapp.generated.resources.enter_email
import socialsync.composeapp.generated.resources.enter_password
import socialsync.composeapp.generated.resources.forget_password
import socialsync.composeapp.generated.resources.sign_in
import socialsync.composeapp.generated.resources.sign_up

@Composable
fun SignIn(
    navController: NavHostController,
    onNavigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }


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
        ) {
            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(Res.string.sign_in),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = stringResource(Res.string.email_address),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 5.dp)
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
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.enter_email),
                            color = Color.LightGray,
                            fontSize = 16.sp,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .height(62.dp)
                        .padding(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Gray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray,
                    )
                )
                Text(
                    text = stringResource(Res.string.create_password),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 5.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.enter_password),
                            color = Color.LightGray,
                            fontSize = 16.sp,
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(if (isPasswordVisible) Resource.Icons.EYE_CLOSED else Resource.Icons.EYE_OPEN),
                            modifier = Modifier
                                .clickable{
                                    isPasswordVisible = !isPasswordVisible
                                },
                            contentDescription = "icon of eye"
                        )
                    },
                    visualTransformation = if (isPasswordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .height(62.dp)
                        .padding(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Gray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray,
                    )
                )
                Text(
                    text = stringResource(Res.string.forget_password),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(End)
                        .padding(8.dp)
                        .clickable{},
                    fontWeight = FontWeight.Normal
                )
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        onNavigateToHome()
                    },
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
                ) {
                    Text(
                        text = stringResource(Res.string.sign_in),
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            val annotatedText = buildAnnotatedString {
                append(stringResource(Res.string.dont_have_account))

                pushStringAnnotation(tag = "SIGN_IN", annotation = "sign_in")
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.SemiBold)) {
                    append(stringResource(Res.string.sign_up))
                }
                pop()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                    .padding(top = 7.dp, bottom = 25.dp),
                contentAlignment = Alignment.Center
            ) {
                ClickableText(
                    text = annotatedText,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(
                            tag = "SIGN_IN",
                            start = offset,
                            end = offset
                        )
                            .firstOrNull()?.let {
                                onNavigateToSignUp()
                            }
                    }
                )
            }

        }
    }
}