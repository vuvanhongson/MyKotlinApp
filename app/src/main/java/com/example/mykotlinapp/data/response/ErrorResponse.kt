package com.example.mykotlinapp.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @Expose
    @SerializedName("error")
    var error: Boolean,
    @Expose
    @SerializedName("code")
    var code: Int,
    @Expose
    @SerializedName("message")
    var message: String
)