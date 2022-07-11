package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.NewLichThu
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataNewLichThu (
    @SerializedName("error")
    @Expose
    var error : Boolean = true,

    @SerializedName("code")
    @Expose
    var code: String = "",

    @SerializedName("message")
    @Expose
    var message: String = "",

    @SerializedName("data")
    @Expose
    var data : NewLichThu
)