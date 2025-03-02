package org.example.socialsync.data.auth.presentaion

sealed class AuthUiIntent {
    data class SignUpEmailChanged(val email: String): AuthUiIntent()
    data class SignUpPasswordChanged(val password: String): AuthUiIntent()
    object SignUp: AuthUiIntent()

    data class SignInEmailChanged(val email: String): AuthUiIntent()
    data class SignInPasswordChanged(val password: String): AuthUiIntent()
    object SignIn: AuthUiIntent()
}