package com.example.mykotlinapp.data.source

import com.example.mykotlinapp.data.source.api.ApiService

class UserRemoteDataSource (private val apiService: ApiService) {

    suspend fun getMovie(page: Int, per_page : Int) = apiService.getMovie(page, per_page)

    suspend fun getLichGomRac(oderby: String, current : Int, number: Int) = apiService.getLichGomRac(oderby, current, number)

    suspend fun getTinh(ten: String) = apiService.getTinhThanhPho(ten)

    suspend fun getDataLoginID(maKhachHang: String) = apiService.getLoginID(maKhachHang)

}