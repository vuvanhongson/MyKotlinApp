package com.example.mykotlinapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mykotlinapp.data.database.DatabaseKey.TABLE_USER
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_USER)
data class User(
    @PrimaryKey
    @Expose
    @SerializedName("id")
    val id: Int = 0,
    @Expose
    @SerializedName("email")
    val email: String = "",
    @Expose
    @SerializedName("full_name")
    val fullName: String = "",
    @Expose
    @SerializedName("access_token")
    val appToken: String = "",
): Parcelable