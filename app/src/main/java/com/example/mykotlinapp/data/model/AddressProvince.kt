package com.example.mykotlinapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressProvince(
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("ten")
    @Expose
    var ten: String = "",

    @SerializedName("full_name")
    @Expose
    var fullName: String = "",
): Parcelable