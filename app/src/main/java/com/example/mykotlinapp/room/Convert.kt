package com.example.mykotlinapp.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class Convert {
    @TypeConverter
    fun objectsToJson(objects: Any?): String? {
        return Gson().toJson(objects)
    }
}