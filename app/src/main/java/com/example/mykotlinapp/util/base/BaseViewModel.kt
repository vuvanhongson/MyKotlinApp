package com.example.mykotlinapp.util.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mykotlinapp.data.response.ErrorResponse
import com.example.mykotlinapp.util.ext.notNull
import com.google.gson.Gson
import org.koin.core.component.KoinComponent
import retrofit2.HttpException

abstract class BaseViewModel : ViewModel(), KoinComponent{

    val error = MutableLiveData<String>()
    fun getErrorResponse(exception : Exception): String?{
        try{
            exception as HttpException
            exception.response().notNull{
                try{
                    val errorResponse =
                            Gson().fromJson(it.errorBody()?.string(), ErrorResponse::class.java)
                    if (errorResponse.error){
                        error.value = errorResponse.message
                    }
                } catch (e : Exception)
                {
                    return e.message
                }
            }
        } catch (e : Exception)
        {
            return e.message
        }
        return ""
    }

}