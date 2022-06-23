package com.example.mykotlinapp.data.source.api

import com.example.mykotlinapp.data.response.DataLichGomRac
import com.example.mykotlinapp.data.response.DataMovie
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @GET("/movie/list")
    suspend fun getMovie(@Query("page") page: Int, @Query("per_page") per_page : Int): DataMovie

    @FormUrlEncoded
    @POST("/api/grac-mobile-app/getLichThuGomRacList")
    suspend fun getLichGomRac(
        @Field("order_by") oderby: String,
        @Field("current_page") current: Int,
        @Field("number_of_record") number: Int
    ): DataLichGomRac
}