package org.example.socialsync.viewmodel.auth

data class AuthState(
    val isLoading: Boolean = false,
    val signupUsername: String = "",
    val signupPassword: String = "",
    val signinUsername: String = "",
    val signinPassword: String = ""
)