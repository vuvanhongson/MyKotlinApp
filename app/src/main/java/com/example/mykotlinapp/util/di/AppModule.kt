package com.example.mykotlinapp.util.di

import com.example.mykotlinapp.api.ConfigEnvironment
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { named(ConfigEnvironment.getBaseURL()) }
}
