package com.example.mykotlinapp.features.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.AddressProvince
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel(private val userRepository: UserRepository) : BaseViewModel(){

    val tinhtp = MutableLiveData<MutableList<AddressProvince>>()

    init {
        getTinh("")
    }

    fun getTinh(ten: String){
        viewModelScope.launch {
            try {
                val data = userRepository.getTinh(ten).dataTinhtp!!
                tinhtp.value = data
                Log.d("api", " - " + data.toString())
            }catch (e: Exception){
                error.value = getErrorResponse(e)
                Log.d("api", " errorapi ")
            }
        }
    }
}