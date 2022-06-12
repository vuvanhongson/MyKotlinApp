package com.example.mykotlinapp.data.database

import com.example.mykotlinapp.data.model.User

interface DatabaseType {
    suspend fun saveUserLocal(user : User) : Long

    suspend fun clearUserLocal()

    suspend fun getUserLocal() : User
}