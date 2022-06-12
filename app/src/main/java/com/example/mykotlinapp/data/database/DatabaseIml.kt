package com.example.mykotlinapp.data.database

import com.example.mykotlinapp.data.model.User

class DatabaseIml (private val databaseManager: DatabaseManager) : DatabaseType{
    override suspend fun saveUserLocal(user: User) = databaseManager.userDao().saveUserLocal(user)

    override suspend fun clearUserLocal() {
        databaseManager.userDao().clearUserLocal()
    }

    override suspend fun getUserLocal() = databaseManager.userDao().getUserLocal()
}