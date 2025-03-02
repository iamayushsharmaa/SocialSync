package org.example.socialsync.data.auth.domain

import org.example.socialsync.core.domain.DataError
import org.example.socialsync.core.domain.Result
import org.example.socialsync.data.auth.model.TokenResponse

interface AuthRepository {
    suspend fun signUp(email: String, password: String) : Result<Unit, DataError.Remote>
    suspend fun signIn(email: String, password: String) : Result<TokenResponse, DataError.Remote>
}