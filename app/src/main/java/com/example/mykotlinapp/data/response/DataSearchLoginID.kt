package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.SearchByLoginID
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class DataSearchLoginID(

    @SerializedName("error")
    @Expose
    var error: Boolean = true,
    @SerializedName("code")
    @Expose
    var code: Int = 0,
    @SerializedName("message")
    @Expose
    var message: String = "",

    @Expose
    @SerializedName("data")
    var dataSearchLoginID: MutableList<SearchByLoginID>? = null,
)
