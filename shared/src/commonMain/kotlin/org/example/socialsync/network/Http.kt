package org.example.socialsync.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine

fun createHttpClient(engine: HttpClientEngine): HttpClient{
    return HttpClient(engine){

    }
}