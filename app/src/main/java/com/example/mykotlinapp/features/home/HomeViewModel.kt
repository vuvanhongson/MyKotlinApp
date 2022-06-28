package com.example.mykotlinapp.features.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.LichGomRac
import com.example.mykotlinapp.data.model.Movie
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel (private val userRepository: UserRepository) : BaseViewModel() {

    var lichgonrac = MutableLiveData<MutableList<LichGomRac>>()
//val movies = MutableLiveData<MutableList<Movie>>()

    init {
        getLich("DESC", 1 , 10 )
    }

    fun getLich(oderby: String, current: Int, number: Int) {
        viewModelScope.launch {
            try {
                var data = userRepository.getLichGomRac(oderby, current, number)
                lichgonrac.value = data.dataLich!!
                Log.d("api", oderby + current + number + " - " + data.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
            }
        }
    }


//    private fun getMovie() {
//        viewModelScope.launch {
//            try {
//                val data = userRepository.getMovie(1, 10)
//                movies.value = data.dataMovie
//            } catch (e: Exception) {
//                error.value = getErrorResponse(e)
//            }
//        }
//    }

}