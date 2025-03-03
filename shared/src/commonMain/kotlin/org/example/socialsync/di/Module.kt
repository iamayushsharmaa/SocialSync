package org.example.socialsync.di

import org.example.socialsync.data.auth.api.AuthApiService
import org.example.socialsync.data.auth.domain.AuthRepository
import org.example.socialsync.data.auth.domain.AuthRepositoryImpl
import org.example.socialsync.data.auth.presentaion.AuthViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val mainModule = module {
    single { AuthApiService(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    viewModel { AuthViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(mainModule)
    }
}

