package com.example.mykotlinapp.util.di

import android.app.Application
import com.example.mykotlinapp.data.di.dataSourceModule
import com.example.mykotlinapp.data.di.network
import com.example.mykotlinapp.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(
            appModule,
            dataSourceModule,
            network,
            repositoryModule,
            viewModelModule
        )
        startKoin {
            logger(logger = PrintLogger(Level.ERROR))
            androidContext(this@MainApplication)
            modules(modules)
        }
    }
}