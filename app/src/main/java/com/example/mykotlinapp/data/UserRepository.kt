package com.example.mykotlinapp.data

import com.example.mykotlinapp.data.source.UserRemoteDataSource

class UserRepository (
    private val remote: UserRemoteDataSource
        ) {

    suspend fun getMovie(page: Int, per_page: Int) = remote.getMovie(page, per_page)

    suspend fun getLichGomRac(oderby: String, current : Int, number: Int) = remote.getLichGomRac(oderby, current, number)

    suspend fun getTinh(ten: String) = remote.getTinh(ten)

    suspend fun getDataLoginID(maKhachHang: String) = remote.getDataLoginID(maKhachHang)

//    suspend fun login(email: String, password: String) = remote.login(email, password)
}