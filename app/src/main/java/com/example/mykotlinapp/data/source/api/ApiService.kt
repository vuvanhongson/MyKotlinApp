package com.example.mykotlinapp.data.source.api

import com.example.mykotlinapp.data.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    suspend fun getQuanHuyen(@Field("tinh_id") tinh_id: Int): DataProvince

    @FormUrlEncoded
    @POST("api/grac-mobile-app/getPhuongXaByQuanId")
    suspend fun getXaPhuong(@Field("quan_id") quan_id: Int): DataProvince

    @FormUrlEncoded
    @POST("/api/grac-mobile-app/traCuuTienRacByLoginID")
    suspend fun getLoginID(@Field("maKhachHang") maKhachHang: String): DataSearchLoginID

    @FormUrlEncoded
    @POST("/api/grac-mobile-app/traCuuTienRacByAddress")
    suspend fun getAddress(@Field("customerAddress") customerAddress: String): DataSearchLoginID

    @Multipart
    @POST("/api/grac-mobile-app/addNewLichThuGomRac")
    suspend fun addNewLichThuGomRac(
        @Part("customerUserName") customerUserNane: RequestBody,
        @Part("tinhThanhPhoId") tinhThanhPhoId: RequestBody,
        @Part("quanHuyenId") quanHuyenId: RequestBody,
        @Part("xaPhuongId") xaPhuongId: RequestBody,
        @Part("shortAddress") shortAddress: RequestBody,
        @Part("khoiLuongThuGom") khoiLuongThuGom: RequestBody,
        @Part("loaiDanhMucRac") loaiDanhMucRac: RequestBody,
        @Part("idDanhMucRac") idDanhMucRac: RequestBody,
        @Part("thoiGianThuGom") thoiGianThuGom: RequestBody,
        @Part("customerPhone") customerPhone: RequestBody,
        @Part("description") description: RequestBody,
        @Part picture_1: MultipartBody.Part?,
        @Part picture_2: MultipartBody.Part?,
        @Part picture_3: MultipartBody.Part?,
    ): DataNewLichThu

    @Multipart
    @POST("/api/grac-mobile-app/addNewKhieuNaiPhanAnh")
    suspend fun addNewKhieuNaiPhanAnh(
        @Part("customerUserName") customerUserNane: RequestBody,
        @Part("customerPhone") customerPhone: RequestBody,
        @Part("customerEmail") customerEmail: RequestBody,
        @Part("description") description: RequestBody,
        @Part("categoryPhanAnhKhieuNai") categoryPhanAnhKhieuNai: RequestBody,
        @Part picture_1: MultipartBody.Part?,
        @Part picture_2: MultipartBody.Part?,
        @Part picture_3: MultipartBody.Part?,
    ): DataComplain

    @Multipart
    @POST("/api/grac-mobile-app/addNewYeuCauPhanLoaiRacThai")
    suspend fun addNewYeuCauPhanLoai(
        @Part("customerUserName") customerUserNane: RequestBody,
        @Part("description") description: RequestBody,
    ): DataNewYeuCauPhanLoai


}