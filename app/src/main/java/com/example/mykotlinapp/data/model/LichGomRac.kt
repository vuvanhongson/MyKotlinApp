package com.example.mykotlinapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LichGomRac (
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @Expose
    @SerializedName("ten_khach_hang")
    var tenKhachHang: String = "",

    @SerializedName("dia_chi")
    @Expose
    var diaChi: String = "",

    @SerializedName("map_lat")
    @Expose
    var mapLat: String = "",

    @SerializedName("map_lng")
    @Expose
    var mapLng: String = "",

    @SerializedName("picture_1")
    @Expose
    var picture1: String = "",

    @SerializedName("picture_2")
    @Expose
    var picture2: String = "",

    @SerializedName("picture_3")
    @Expose
    var picture3: String = "",

    @SerializedName("ngay_dang")
    @Expose
    var ngayDang: String = ""
): Parcelable