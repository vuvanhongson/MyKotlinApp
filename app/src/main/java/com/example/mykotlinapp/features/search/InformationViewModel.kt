package com.example.mykotlinapp.features.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.SearchByLoginID
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch

class InformationViewModel (private val userRepository: UserRepository) : BaseViewModel() {

    var loginID = MutableLiveData<MutableList<SearchByLoginID>>()

    fun getLoginID(maKhachHang: String) {
        viewModelScope.launch {
            try {
                val data = userRepository.getDataLoginID(maKhachHang)
                loginID.value = data.dataSearchLoginID!!
                Log.d("api_loginid", "$maKhachHang - $data")
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
            }
        }
    }
}