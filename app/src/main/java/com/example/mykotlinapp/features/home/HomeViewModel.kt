package com.example.mykotlinapp.features.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.LichGomRac
import com.example.mykotlinapp.data.model.Movie
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel (private var userRepository: UserRepository) : BaseViewModel() {

    var lichgonracDESC = MutableLiveData<MutableList<LichGomRac>>()
    var lichgonracASC = MutableLiveData<MutableList<LichGomRac>>()


//    init {
////        getLich("DESC", 1 , 10 )
//    }

    fun getLich(oderby: String, current: Int, number: Int) {
        lichgonracDESC = MutableLiveData<MutableList<LichGomRac>>()
        viewModelScope.launch {
            try {
                var data = userRepository.getLichGomRac(oderby, current, number)
                lichgonracDESC.value = data.dataLich!!
                Log.d("api", oderby + current + number + " - " + data.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
            }
        }
    }


}