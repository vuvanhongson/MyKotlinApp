package com.example.mykotlinapp.data.source

import com.example.mykotlinapp.data.source.api.ApiService
import retrofit2.http.Field
import retrofit2.http.Part

class UserRemoteDataSource (private val apiService: ApiService) {

    suspend fun getMovie(page: Int, per_page : Int) = apiService.getMovie(page, per_page)

    suspend fun getLichGomRac(oderby: String, current : Int, number: Int) = apiService.getLichGomRac(oderby, current, number)

    suspend fun getTinh(ten: String) = apiService.getTinhThanhPho(ten)

    suspend fun getDataLoginID(maKhachHang: String) = apiService.getLoginID(maKhachHang)

    suspend fun addNewLichThuGomRac(
        customerUserNane: String,
        tinhThanhPhoId: Int,
        quanHuyenId: Int,
        xaPhuongId: Int,
        shortAddress: String,
        khoiLuongThuGom: Int,
        categoryRacThaiDacBietId: Int,
        categoryDangKyThuRacId: Int,
        thoiGianThuGom: String,
        customerPhone: String,
        description: String,
        picture_1: Part?,
        picture_2: Part?,
        picture_3: Part?
    ) = apiService.addNewLichThuGomRac(
        customerUserNane,
        tinhThanhPhoId,
        quanHuyenId,
        xaPhuongId,
        shortAddress,
        khoiLuongThuGom,
        categoryRacThaiDacBietId,
        categoryDangKyThuRacId,
        thoiGianThuGom,
        customerPhone,
        description,
        picture_1,
        picture_2,
        picture_3)

}