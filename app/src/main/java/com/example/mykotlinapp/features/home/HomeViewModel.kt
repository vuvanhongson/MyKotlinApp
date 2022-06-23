package com.example.mykotlinapp.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.LichGomRac
import com.example.mykotlinapp.data.model.Movie
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel (private val userRepository: UserRepository) : BaseViewModel() {

    val lichgonrac = MutableLiveData<MutableList<LichGomRac>>()
//val movies = MutableLiveData<MutableList<Movie>>()

    init {
        getMovie()
    }

    private fun getMovie() {
        viewModelScope.launch {
            try {
                val data = userRepository.getLichGomRac("DESC",1, 10)
                lichgonrac.value = data.dataLich
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