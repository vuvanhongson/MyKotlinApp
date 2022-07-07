package com.example.mykotlinapp.data

import com.example.mykotlinapp.data.source.UserRemoteDataSource
import retrofit2.http.Part

class UserRepository(
    private val remote: UserRemoteDataSource
) {

    suspend fun getMovie(page: Int, per_page: Int) = remote.getMovie(page, per_page)

    suspend fun getLichGomRac(oderby: String, current: Int, number: Int) =
        remote.getLichGomRac(oderby, current, number)

    suspend fun getTinh(ten: String) = remote.getTinh(ten)

    suspend fun getDataLoginID(maKhachHang: String) = remote.getDataLoginID(maKhachHang)

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
    ) = remote.addNewLichThuGomRac(
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

//    suspend fun login(email: String, password: String) = remote.login(email, password)
}