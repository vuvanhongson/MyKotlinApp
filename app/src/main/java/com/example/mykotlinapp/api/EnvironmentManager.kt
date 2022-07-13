package com.example.mykotlinapp.api

object EnvironmentManager {

    enum class ENV{
        DEV,
        PRODUCTION
    }

    var env : ENV = ENV.DEV

    const val DEV_URL : String = "https://e.grac.vn"
    const val PRODUCTION_URL = "http://192.168.10.91:82"
    const val DEV_TOKEN = "ab4535e7cf80c3b8067ab00954510610d6874536"
    const val PRODUCTION_TOKEN = "ab4535e7cf80c3b8067ab00954510610d6874536"
    //làm như trên

}