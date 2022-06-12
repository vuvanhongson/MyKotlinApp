package com.example.mykotlinapp.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mykotlinapp.data.database.DatabaseKey.TABLE_USER

object MigrationManager {
    val MIGRATION_1_2 = object : Migration(1, 2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE $TABLE_USER ADD COLUMN 'whiteList' INTEGER DEFAULT 0")
        }

    }
}