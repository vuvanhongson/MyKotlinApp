package com.example.mykotlinapp.data.source

import com.example.mykotlinapp.data.source.api.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.Part

class UserRemoteDataSource (private val apiService: ApiService) {

    suspend fun getMovie(page: Int, per_page : Int) = apiService.getMovie(page, per_page)

    suspend fun getLichGomRac(oderby: String, current : Int, number: Int) = apiService.getLichGomRac(oderby, current, number)

    suspend fun getTinh(ten: String) = apiService.getTinhThanhPho(ten)

    suspend fun getHuyen(tinh_id: Int) = apiService.getQuanHuyen(tinh_id)

    suspend fun getXa(quan_id: Int) = apiService.getXaPhuong(quan_id)

    suspend fun getDataLoginID(maKhachHang: String) = apiService.getLoginID(maKhachHang)

    suspend fun getAddress(customerAddress: String) = apiService.getAddress(customerAddress)

    suspend fun addNewLichThuGomRac(
        customerUserNane: RequestBody,
        tinhThanhPhoId: RequestBody,
        quanHuyenId: RequestBody,
        xaPhuongId: RequestBody,
        shortAddress: RequestBody,
        khoiLuongThuGom: RequestBody,
        loaiDanhMucRac: RequestBody,
        idDanhMucRac: RequestBody,
        thoiGianThuGom: RequestBody,
        customerPhone: RequestBody,
        description: RequestBody,
        picture_1: MultipartBody.Part?,
        picture_2: MultipartBody.Part?,
        picture_3: MultipartBody.Part?,
    ) = apiService.addNewLichThuGomRac(
        customerUserNane,
        tinhThanhPhoId,
        quanHuyenId,
        xaPhuongId,
        shortAddress,
        khoiLuongThuGom,
        loaiDanhMucRac,
        idDanhMucRac,
        thoiGianThuGom,
        customerPhone,
        description,
        picture_1!!,
        picture_2!!,
        picture_3!!,
    )

    suspend fun addNewKhieuNaiPhanAnh(
        customerUserNane: RequestBody,
        customerPhone: RequestBody,
        customerEmail: RequestBody,
        description: RequestBody,
        categoryPhanAnhKhieuNai: RequestBody,
        picture_1: MultipartBody.Part?,
        picture_2: MultipartBody.Part?,
        picture_3: MultipartBody.Part?,
    ) = apiService.addNewKhieuNaiPhanAnh(
        customerUserNane,
        customerPhone,
        customerEmail,
        description,
        categoryPhanAnhKhieuNai,
        picture_1!!,
        picture_2!!,
        picture_3!!,
    )

    suspend fun addNewYeuCauPhanLoai(
        customerUserNane: RequestBody,
        description: RequestBody,
    ) = apiService.addNewYeuCauPhanLoai(
        customerUserNane,
        description,
    )

}