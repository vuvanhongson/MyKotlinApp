package com.example.mykotlinapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewLichThu (
    @SerializedName("accept")
    @Expose
    private val accept: Boolean? = null
): Parcelable