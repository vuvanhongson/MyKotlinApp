package com.example.mykotlinapp.data.di

import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import com.example.mykotlinapp.data.database.*
import com.example.mykotlinapp.data.source.UserRemoteDataSource
import org.koin.dsl.module

private fun appDatabase(context: Context): DatabaseManager =
    Room.databaseBuilder(context, DatabaseManager::class.java, DatabaseKey.DATABASE_NAME)
        .addMigrations(MigrationManager.MIGRATION_1_2).build()

private fun contentResolver(context : Context) : ContentResolver = context.contentResolver

val dataSourceModule =  module {

    single { contentResolver(context = get()) }

    single { appDatabase(context = get()) }

    single<DatabaseType> { DatabaseIml(databaseManager =  get())  }

    single{ UserRemoteDataSource(apiService = get())}

}