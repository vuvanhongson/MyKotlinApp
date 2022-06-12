package com.example.mykotlinapp.data.source.api

import com.example.mykotlinapp.data.source.response.DataMovie
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @GET("/movie/list")
    suspend fun getMovie(@Query("page") page: Int, @Query("per_page") per_page : Int): DataMovie
}