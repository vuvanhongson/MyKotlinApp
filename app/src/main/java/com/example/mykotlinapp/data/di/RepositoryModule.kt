package com.example.mykotlinapp.data.di

import com.example.mykotlinapp.data.UserRepositiry
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepositiry(remote = get()) }
}