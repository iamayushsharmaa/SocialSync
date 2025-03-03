package org.example.socialsync.data.auth.presentaion


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.socialsync.core.domain.DataError
import org.example.socialsync.core.domain.Result
import org.example.socialsync.data.auth.domain.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun onIntent(intent: AuthUiIntent) {
        when (intent) {
            is AuthUiIntent.SignUpEmailChanged -> updateSignupEmail(intent.email)
            is AuthUiIntent.SignUpPasswordChanged -> updateSignupPassword(intent.password)
            is AuthUiIntent.SignInEmailChanged -> updateSigninEmail(intent.email)
            is AuthUiIntent.SignInPasswordChanged -> updateSigninPassword(intent.password)
            is AuthUiIntent.SignUp -> signUp()
            is AuthUiIntent.SignIn -> signIn()
        }
    }

    private fun updateSignupEmail(email: String) {
        _state.value = _state.value.copy(signupEmail = email)
    }

    private fun updateSignupPassword(password: String) {
        _state.value = _state.value.copy(signupPassword = password)
    }
    private fun updateSigninEmail(email: String) {
        _state.value = _state.value.copy(signupEmail = email)
    }

    private fun updateSigninPassword(password: String) {
        _state.value = _state.value.copy(signupPassword = password)
    }

    private fun signUp() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = repository.signUp(
                email = state.value.signupEmail,
                password = state.value.signupPassword
            )
            when (result) {
                is Result.Success -> {
                    _state.update { it.copy(isLoading = false) }
                }
                is Result.Error -> {
                    val errorMessage = when (result.error) {
                        DataError.Remote.NO_INTERNET -> "No internet connection"
                        DataError.Remote.REQUEST_TIMEOUT -> "Request timed out"
                        DataError.Remote.SERIALIZATION -> "Data parsing error"
                        DataError.Remote.SERVER -> "Server error"
                        DataError.Remote.TOO_MANY_REQUESTS -> "Too many requests"
                        DataError.Remote.UNKNOWN -> "Unknown error"
                    }
                    _state.update { it.copy(isLoading = false, error = errorMessage) }
                }
            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            val result = repository.signIn(
                email = state.value.signinEmail,
                password = state.value.signinPassword
            )

            when (result) {
                is Result.Success -> {

                    _state.update { it.copy(isLoading = false) }
                }
                is Result.Error -> {
                    val errorMessage = when (result.error) {
                        DataError.Remote.NO_INTERNET -> "No internet connection"
                        DataError.Remote.REQUEST_TIMEOUT -> "Request timed out"
                        DataError.Remote.SERIALIZATION -> "Data parsing error"
                        DataError.Remote.SERVER -> "Server error"
                        DataError.Remote.TOO_MANY_REQUESTS -> "Too many requests"
                        DataError.Remote.UNKNOWN -> "Unknown error"
                    }
                    _state.update { it.copy(isLoading = false, error = errorMessage) }
                }
            }
        }
    }
}