package com.example.mykotlinapp.data.source.api

import com.example.mykotlinapp.data.response.*
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

    @FormUrlEncoded
    @POST("api/grac-mobile-app/getTinhThanhPho")
    suspend fun getTinhThanhPho(@Field("ten") ten: String): DataProvince

    @FormUrlEncoded
    @POST("api/grac-mobile-app/getQuanHuyenByTinhId")
    suspend fun getQuanHuyen(@Field("tinh_id") tinh_id: Int): DataDistrict

    @FormUrlEncoded
    @POST("api/grac-mobile-app/getPhuongXaByQuanId")
    suspend fun getXaPhuong(@Field("quan_id") quan_id: Int): DataCommune

    @FormUrlEncoded
    @POST("/api/grac-mobile-app/traCuuTienRacByLoginID")
    suspend fun getLoginID(@Field("maKhachHang") maKhachHang: String): DataSearchLoginID
}