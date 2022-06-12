package com.example.mykotlinapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mykotlinapp.data.database.DatabaseKey.DB_VERSION
import com.example.mykotlinapp.data.model.User
import com.example.mykotlinapp.room.Convert
import com.example.mykotlinapp.room.dao.UserDao

@Database(entities = [User::class], version = DB_VERSION)
@TypeConverters(Convert::class)
abstract  class DatabaseManager : RoomDatabase()
{
    abstract fun userDao(): UserDao
}