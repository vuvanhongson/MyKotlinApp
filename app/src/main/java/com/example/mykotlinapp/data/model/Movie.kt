package com.example.mykotlinapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    @Expose
    @SerializedName("id")
    var id: Int = 0,
    @Expose
    @SerializedName("title")
    var title: String = "",
    @Expose
    @SerializedName("image")
    var image: String = "",
    @Expose
    @SerializedName("link")
    var link: String = "",
    @Expose
    @SerializedName("description")
    var description: String = "",
    @Expose
    @SerializedName("category")
    var category: String = "",
    @Expose
    @SerializedName("actor")
    var actor: String = "",
    @Expose
    @SerializedName("director")
    var director: String = "",
    @Expose
    @SerializedName("manufacturer")
    var manufacturer: String = "",
    @Expose
    @SerializedName("duration")
    var duration: String = "",
    @Expose
    @SerializedName("views")
    var views: Int = 0
) : Parcelable