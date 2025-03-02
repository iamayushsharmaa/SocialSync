package org.example.socialsync.data.auth.presentaion

data class AuthState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val signupEmail: String = "",
    val signupPassword: String = "",
    val signinEmail: String = "",
    val signinPassword: String = ""
)