package com.example.mykotlinapp.api

object EnvironmentManager {

    enum class ENV{
        DEV,
        PRODUCTION
    }

    var env : ENV = ENV.DEV;

    const val DEV_URL : String = "http://192.168.10.51:82/api/grac-mobile-app"
    const val PRODUCTION_URL = "https://"
    const val BASE_URL = "http://training-movie.bsp.vn:82"
    const val IMAGE_URL = "/upload/movie/ben-hur.jpg"
    const val APP_TOKEN = "ab4535e7cf80c3b8067ab00954510610d6874536"
    //làm như trên

}