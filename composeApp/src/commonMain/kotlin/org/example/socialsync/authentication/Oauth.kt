package org.example.socialsync.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import socialsync.composeapp.generated.resources.Res
import socialsync.composeapp.generated.resources.already_have_account
import socialsync.composeapp.generated.resources.continue_email
import socialsync.composeapp.generated.resources.continue_google
import socialsync.composeapp.generated.resources.continue_x
import socialsync.composeapp.generated.resources.explore_the_app
import socialsync.composeapp.generated.resources.now_your_finances_in_one_place
import socialsync.composeapp.generated.resources.sign_in

@Composable
fun OauthSignIn(
    navController: NavHostController,
    onGoogleClick: () -> Unit ,
    onXClick: () -> Unit,
    onEmailClick: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = Color.White)
    ){
        Spacer(Modifier.weight(1f))

        OauthBoxDesign(
            modifier = Modifier
                .padding(16.dp),
            onGoogleClick = {
                onGoogleClick()
            },
            onXClick = {
                onXClick()
            },
            onEmailClick = {
                onEmailClick()
            }
        )

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

@Composable
fun OauthBoxDesign(
    modifier: Modifier = Modifier,
    onGoogleClick: () -> Unit,
    onXClick: () -> Unit,
    onEmailClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(640.dp)
            .padding(16.dp)
            .background(shape = RoundedCornerShape(22.dp), color = Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ){
        Spacer(Modifier.weight(1f))
        Text(
            text = stringResource(Res.string.explore_the_app),
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = stringResource(Res.string.now_your_finances_in_one_place),
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
        Spacer(Modifier.height(20.dp))
        OauthButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            icon = Resource.Icons.GOOGLE,
            text = Res.string.continue_google,
            onClick = {
                onGoogleClick()
            }
        )
        OauthButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            icon = Resource.Icons.X,
            text = Res.string.continue_x,
            onClick = {
                onXClick()
            }
        )
        OauthButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            icon = Resource.Icons.EMAIL,
            text = Res.string.continue_email,
            onClick = {
                onEmailClick()
            }
        )

        Spacer(Modifier.height(16.dp))

    }
}

@Composable
fun OauthButton(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    text: StringResource,
    onClick: () -> Unit = {}
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable{
                onClick()
            }
            .background(shape = RoundedCornerShape(18.dp), color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Icon(
            painter = painterResource(icon),
            tint = Color.Unspecified,
            modifier = Modifier.size(28.dp),
            contentDescription = "icon of social"
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = stringResource(text),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}