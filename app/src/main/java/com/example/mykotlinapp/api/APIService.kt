package com.example.mykotlinapp.api

import com.example.mykotlinapp.data.source.middleware.CoroutineCallAdapterFactory
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.http.BridgeInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object APIService {

   fun <T> generate(
       baseUrl : String,
       serviceClass: Class <T>,
       gson : Gson,
       interceptor: List<Interceptor>
   ): T {
       val okHttpClient = buildOkhttpClient(interceptor)
       val builder = Retrofit.Builder()
           .baseUrl(baseUrl)
           .addCallAdapterFactory(CoroutineCallAdapterFactory())
           .addConverterFactory(GsonConverterFactory.create(gson))
           .client(okHttpClient)
           .build()
       return builder.create(serviceClass)

   }

    private fun buildOkhttpClient(interceptors: List<Interceptor>): OkHttpClient {
        return OkHttpClient.Builder().apply {
            for (i in interceptors) addInterceptor(i)
            connectTimeout(20000, TimeUnit.MILLISECONDS)
            readTimeout(20000, TimeUnit.MILLISECONDS)
            writeTimeout(20000, TimeUnit.MILLISECONDS)
        }.build()
    }
}

