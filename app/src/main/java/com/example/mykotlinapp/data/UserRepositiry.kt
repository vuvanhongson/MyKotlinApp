package com.example.mykotlinapp.data

import com.example.mykotlinapp.data.source.UserRemoteDataSource

class UserRepositiry (
    private val remote: UserRemoteDataSource
        ) {

    suspend fun getMovie(page: Int, per_page: Int) = remote.getMovie(page, per_page)

//    suspend fun login(email: String, password: String) = remote.login(email, password)
}