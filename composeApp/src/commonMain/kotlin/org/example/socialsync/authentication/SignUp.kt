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
import androidx.compose.foundation.layout.size
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
import socialsync.composeapp.generated.resources.already_have_account
import socialsync.composeapp.generated.resources.confirm_password
import socialsync.composeapp.generated.resources.create_password
import socialsync.composeapp.generated.resources.email_address
import socialsync.composeapp.generated.resources.enter_email
import socialsync.composeapp.generated.resources.must_be_8_char
import socialsync.composeapp.generated.resources.repeat_password
import socialsync.composeapp.generated.resources.sign_in

@Composable
fun SignUp(
    navController: NavHostController,
    onNavigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("")  }
    var isNewPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
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
                text = stringResource(Res.string.email_address),
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
                    text = stringResource(Res.string.enter_email),
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
                text = stringResource(Res.string.create_password),
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 3.dp)
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
                placeholder = { Text(
                    text = stringResource(Res.string.must_be_8_char),
                    color = Color.LightGray,
                    fontSize = 16.sp,
                ) },
                trailingIcon = {
                    Icon(
                        painter = painterResource(if (isNewPasswordVisible) Resource.Icons.EYE_OPEN else Resource.Icons.EYE_CLOSED),
                        modifier = Modifier
                            .size(22.dp)
                            .clickable{
                                isNewPasswordVisible = !isNewPasswordVisible
                            },
                        contentDescription = "icon of eye"
                    )
                },
                visualTransformation = if (isNewPasswordVisible)
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
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.LightGray
                )
            )
            Text(
                text = stringResource(Res.string.confirm_password),
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 3.dp)
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                shape = RoundedCornerShape(12.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,

                    ),
                placeholder = { Text(
                    text = stringResource(Res.string.repeat_password),
                    color = Color.LightGray,
                    fontSize = 16.sp,
                ) },
                trailingIcon = {
                    Icon(
                        painter = painterResource(if (isConfirmPasswordVisible) Resource.Icons.EYE_OPEN else Resource.Icons.EYE_CLOSED),
                        modifier = Modifier
                            .size(22.dp)
                            .clickable{
                                isConfirmPasswordVisible = !isConfirmPasswordVisible
                            },
                        contentDescription = "icon of eye"
                    )
                },
                visualTransformation = if (isConfirmPasswordVisible)
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
                onClick = { onNavigateToHome() },
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
            append(stringResource(Res.string.already_have_account))

            pushStringAnnotation(tag = "SIGN_IN", annotation = "sign_in")
            withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.SemiBold)) {
                append(stringResource(Res.string.sign_in))
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
                            onNavigateToSignIn()
                        }
                }
            )
        }

    }
}
