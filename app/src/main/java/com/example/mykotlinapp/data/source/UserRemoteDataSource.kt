package com.example.mykotlinapp.data.source

import com.example.mykotlinapp.data.source.api.ApiService

class UserRemoteDataSource (private val apiService: ApiService) {

    suspend fun getMovie(page: Int, per_page : Int) = apiService.getMovie(page, per_page)

}