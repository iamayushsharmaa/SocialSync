package org.example.socialsync.data.auth.presentaion

sealed class AuthUiIntent {
    data class SignUpUsernameChanged(val value: String): AuthUiIntent()
    data class SignUpPasswordChanged(val value: String): AuthUiIntent()
    object SignUp: AuthUiIntent()

    data class SignInUsernameChanged(val value: String): AuthUiIntent()
    data class SignInPasswordChanged(val value: String): AuthUiIntent()
    object SignIn: AuthUiIntent()
}