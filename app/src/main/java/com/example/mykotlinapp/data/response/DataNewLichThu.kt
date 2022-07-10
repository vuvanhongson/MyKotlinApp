package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.NewLichThu
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataNewLichThu (
    @SerializedName("data")
    @Expose
    var data : MutableList<NewLichThu>,
    @SerializedName("error")
    @Expose
    var error : Boolean = true,
    @SerializedName("code")
    @Expose
    var code: Int = 0,
    @SerializedName("message")
    @Expose
    var message: String = "",
)