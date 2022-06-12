package com.example.mykotlinapp.data.di

import com.example.mykotlinapp.api.APIService
import com.example.mykotlinapp.api.ConfigEnvironment
import com.example.mykotlinapp.data.source.api.ApiService
import com.example.mykotlinapp.data.source.middleware.InterceptorIml
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

private fun builHttpLog(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

val network = module{
    single<Gson> {
        GsonBuilder().setPrettyPrinting().serializeNulls().excludeFieldsWithoutExposeAnnotation().create()
    }

    single<Interceptor> {
        InterceptorIml()
    }

    single {
        APIService.generate(
            baseUrl = ConfigEnvironment.getBaseURL(),
            serviceClass = ApiService::class.java,
            gson = get(),
            interceptor = listOf(builHttpLog(), get())
        )
    }
}