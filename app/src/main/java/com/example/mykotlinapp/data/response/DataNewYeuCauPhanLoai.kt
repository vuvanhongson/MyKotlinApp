package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.NewYeuCauPhanLoai
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataNewYeuCauPhanLoai(
    @SerializedName("error")
    @Expose
    var error: Boolean = true,

    @SerializedName("code")
    @Expose
    var code: String = "",

    @SerializedName("message")
    @Expose
    var message: String = "",

    @SerializedName("data")
    @Expose
    var data: NewYeuCauPhanLoai
)