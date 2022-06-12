package com.example.mykotlinapp.api

object EnvironmentManager {

    enum class ENV{
        DEV,
        PRODUCTION
    }

    var env : ENV = ENV.DEV;

    const val DEV_URL : String = "https://"
    const val PRODUCTION_URL = "https://"
    const val BASE_URL = "http://training-movie.bsp.vn:82"
    const val IMAGE_URL = "/upload/movie/ben-hur.jpg"
    const val APP_TOKEN = "dCuW7UQMbdvpcBDfzolAOSGFIcAec11a"
    //làm như trên

}