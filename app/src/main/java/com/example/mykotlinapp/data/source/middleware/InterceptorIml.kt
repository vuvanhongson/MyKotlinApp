package com.example.mykotlinapp.data.source.middleware

import com.example.mykotlinapp.api.ConfigEnvironment
import com.example.mykotlinapp.util.Constant
import okhttp3.Interceptor
import okhttp3.Response

class InterceptorIml() : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
//        val config : String = ConfigEnvironment.getToken()

        val headers = originRequest
            .url
            .newBuilder()
            .build()

        val headerRequest = originRequest.newBuilder()
            .url(headers)
            .addHeader( "Content-Type", "application/json")
            .addHeader("app_token" , ConfigEnvironment.getToken())
            .method(originRequest.method, originRequest.body)
        return chain.proceed(headerRequest.build())

    }


}