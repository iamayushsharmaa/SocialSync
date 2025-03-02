package org.example.socialsync.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single<AuthApiService> { AuthApiServiceImpl() }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    viewModel { AuthViewModel(get()) }
}