package org.example.socialsync.data.posts.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.example.socialsync.app.PermissionsViewModel
import org.example.socialsync.data.posts.data.SocialApi
import org.example.socialsync.data.posts.data.SocialApiImpl
import org.example.socialsync.data.posts.domain.SocialMediaRepository
import org.example.socialsync.data.posts.domain.SocialMediaRepositoryImpl
import org.example.socialsync.data.posts.presentation.PostViewModel
import org.example.socialsync.di.mainModule
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val postModule = module {
    single { createHttpClient() }

    single<SocialMediaRepository> { SocialMediaRepositoryImpl(get()) }
    single<SocialApi> { SocialApiImpl(get()) }

    single<CoroutineScope> {
        CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    viewModel { PostViewModel(get(), get()) }
    viewModel { PermissionsViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(mainModule)
    }
}

fun createHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
}