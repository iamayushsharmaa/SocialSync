package org.example.socialsync.app

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

interface Platform {
    val name: String
}

//expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

//expect fun getPlatform(): Platform