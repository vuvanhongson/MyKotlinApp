package com.example.mykotlinapp.data.response

import com.example.mykotlinapp.data.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataMovie(
    @Expose
    @SerializedName("data")
    var dataMovie: MutableList<Movie>
)