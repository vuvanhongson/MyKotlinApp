package com.example.mykotlinapp.features.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.LichGomRac
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch

class MapViewModel (private val userRepository: UserRepository) : BaseViewModel() {

    var lichgonracDESC = MutableLiveData<MutableList<LichGomRac>>()


    init {
        getLich( 1 , 50 )
    }

    fun getLich(current: Int, number: Int) {
        lichgonracDESC = MutableLiveData<MutableList<LichGomRac>>()
        viewModelScope.launch {
            try {
                val data = userRepository.getLichGomRac("DESC", current, number)
                lichgonracDESC.value = data.dataLich!!
                Log.d("apiMap", "DESC - " + current + number + " - " + data.toString())
            } catch (e: Exception) {
                error("lỗi gọi api")
            }
        }
    }



}