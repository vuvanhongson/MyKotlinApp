package com.example.mykotlinapp.data

import com.example.mykotlinapp.data.source.UserRemoteDataSource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

class UserRepository(
    private val remote: UserRemoteDataSource
) {

    suspend fun getMovie(page: Int, per_page: Int) = remote.getMovie(page, per_page)

    suspend fun getLichGomRac(oderby: String, current: Int, number: Int) =
        remote.getLichGomRac(oderby, current, number)

    suspend fun getTinh(ten: String) = remote.getTinh(ten)

    suspend fun getHuyen(tinh_id: Int) = remote.getHuyen(tinh_id)

    suspend fun getXa(quan_id: Int) = remote.getXa(quan_id)

    suspend fun getDataLoginID(maKhachHang: String) = remote.getDataLoginID(maKhachHang)

    suspend fun getAddress(customerAddress: String) = remote.getAddress(customerAddress)

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
    ) = remote.addNewLichThuGomRac(
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
    ) = remote.addNewKhieuNaiPhanAnh(
        customerUserNane,
        customerPhone,
        customerEmail,
        description,
        categoryPhanAnhKhieuNai,
        picture_1!!,
        picture_2!!,
        picture_3!!,
    )


//    suspend fun login(email: String, password: String) = remote.login(email, password)
}