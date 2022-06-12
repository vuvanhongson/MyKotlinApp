package com.example.mykotlinapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mykotlinapp.data.database.DatabaseKey.TABLE_USER
import com.example.mykotlinapp.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserLocal(user : User) : Long

    @Query("DELETE FROM $TABLE_USER")
    suspend fun clearUserLocal()

    @Query("SELECT * FROM $TABLE_USER")
    suspend fun getUserLocal(): User
}