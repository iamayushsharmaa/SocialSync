package org.example.socialsync.data.auth.domain

import org.example.socialsync.core.domain.DataError
import org.example.socialsync.core.domain.Result
import org.example.socialsync.data.auth.api.AuthApiService
import org.example.socialsync.data.auth.model.AuthRequest
import org.example.socialsync.data.auth.model.TokenResponse


class AuthRepositoryImpl(
    private val apiService: AuthApiService
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): Result<Unit, DataError.Remote> {
        return apiService.signUp(AuthRequest(email, password))
    }

    override suspend fun signIn(email: String, password: String): Result<TokenResponse, DataError.Remote> {
        return apiService.signIn(AuthRequest(email, password))
    }
}