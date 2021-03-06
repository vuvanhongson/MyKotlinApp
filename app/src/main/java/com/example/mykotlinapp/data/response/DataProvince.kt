package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.AddressProvince
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataProvince(
    @Expose
    @SerializedName("data")
    var dataTinhtp: MutableList<AddressProvince>? = null,
    @SerializedName("error")
    @Expose
    var error: Boolean = true,
    @SerializedName("code")
    @Expose
    var code: Int = 0,
    @SerializedName("message")
    @Expose
    var message: String = "",
)