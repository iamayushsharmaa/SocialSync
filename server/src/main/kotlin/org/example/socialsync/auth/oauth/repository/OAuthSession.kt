package org.example.socialsync.auth.oauth.repository

import io.ktor.client.HttpClient
import io.ktor.server.application.ApplicationCall
import org.example.socialsync.auth.oauth.model.UserInfo
import org.example.socialsync.auth.oauth.model.UserSession


interface OAuthSession {

    suspend fun getPersonalGreeting(
        httpClient: HttpClient,
        userSession: UserSession
    ): UserInfo

    suspend fun getSession(
        call: ApplicationCall
    ): UserSession?
}