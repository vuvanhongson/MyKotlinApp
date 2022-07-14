package com.example.mykotlinapp.features.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.LichGomRac
import com.example.mykotlinapp.data.model.SearchByLoginID
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    var lichgonracDESC = MutableLiveData<MutableList<LichGomRac>>()
    var lichgonracASC = MutableLiveData<MutableList<LichGomRac>>()
    var loginID = MutableLiveData<MutableList<SearchByLoginID>>()
    var isSuccess = MutableLiveData<Boolean>()


//    init {
////        getLich("DESC", 1 , 10 )
//    }

    fun getLichDESC(current: Int, number: Int) {
        lichgonracDESC = MutableLiveData<MutableList<LichGomRac>>()
        viewModelScope.launch {
            try {
                val data = userRepository.getLichGomRac("DESC", current, number)
                lichgonracDESC.value = data.dataLich!!
//                Log.d("api", "DESC - " + current + number + " - " + data.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
            }
        }
    }

    fun getLichASC(current: Int, number: Int) {
        lichgonracASC = MutableLiveData<MutableList<LichGomRac>>()
        viewModelScope.launch {
            try {
                val data = userRepository.getLichGomRac("ASC", current, number)
                lichgonracASC.value = data.dataLich!!
                Log.d("api", "ASC - " + current + number + " - " + data.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
            }
        }
    }

    fun getLoginID(maKhachHang: String) {
        Log.d("api_loginid", "$maKhachHang")
        viewModelScope.launch {
            try {
                loginID = MutableLiveData<MutableList<SearchByLoginID>>()
                val data = userRepository.getDataLoginID(maKhachHang)
                if(data.code == 200)
                {
                    loginID.value = data.dataSearchLoginID!!
                    isSuccess.value = true
                    Log.d("api_loginid", "$maKhachHang - $data")
                }
                else
                {
                    isSuccess.value = false
                }

            } catch (e: Exception) {
                isSuccess.value = false
                error.value = getErrorResponse(e)
                Log.d("api_loginid", "lỗi api")
            }
        }
    }

}