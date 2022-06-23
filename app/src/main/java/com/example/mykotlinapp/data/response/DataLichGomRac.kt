package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.LichGomRac
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DataLichGomRac (

    @SerializedName("status")
    @Expose
    var status: String = "",

    @SerializedName("message")
    @Expose
    var message: String = "",

    @SerializedName("data")
    @Expose
    var dataLich: MutableList<LichGomRac>
)