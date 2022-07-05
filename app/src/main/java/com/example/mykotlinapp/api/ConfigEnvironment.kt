package com.example.mykotlinapp.api

class ConfigEnvironment {

    companion object {
        fun getBaseURL() : String {
            if(EnvironmentManager.env == EnvironmentManager.ENV.DEV)
            {
                return EnvironmentManager.DEV_URL
            }
            return EnvironmentManager.PRODUCTION_URL
        }

        fun getToken(): String {
            if (EnvironmentManager.env == EnvironmentManager.ENV.DEV) {
                return EnvironmentManager.DEV_TOKEN
            }
            return EnvironmentManager.PRODUCTION_TOKEN
        }
    }
}