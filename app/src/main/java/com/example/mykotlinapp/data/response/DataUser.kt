package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataUser(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("data")
    val data: User
)