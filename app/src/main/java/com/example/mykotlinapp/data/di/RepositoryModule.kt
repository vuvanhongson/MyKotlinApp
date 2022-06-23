package com.example.mykotlinapp.data.di

import com.example.mykotlinapp.data.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(remote = get()) }
}