package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.AddressDistrict;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class DataDistrict(
    @Expose
    @SerializedName("data")
    var dataTinhtp: MutableList<AddressDistrict>? = null,
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