package com.example.mykotlinapp.data.source.middleware

import com.example.mykotlinapp.api.ConfigEnvironment
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class InterceptorIml() : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
//        val config : String = ConfigEnvironment.getToken()

        val headers = originRequest
            .url
            .newBuilder()
            .setEncodedQueryParameter("token", ConfigEnvironment.getToken())
            .build()


        val headerRequest = originRequest.newBuilder()
            .url(headers)
            .addHeader("Content-Type", "application/json")
            .method(originRequest.method, originRequest.body)
        return chain.proceed(headerRequest.build())

    }


}