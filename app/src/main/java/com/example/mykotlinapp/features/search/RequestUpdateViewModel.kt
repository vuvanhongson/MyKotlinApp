package com.example.mykotlinapp.features.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.NewYeuCauPhanLoai
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.Exception

class RequestUpdateViewModel (private val userRepository: UserRepository) : BaseViewModel() {

    val NewYeuCauPhanLoai = MutableLiveData<NewYeuCauPhanLoai>()

    fun addNewYeuCauPhanLoai(
        customerUserNane: String,
        description: String,
    ) {
        viewModelScope.launch {
            val customerUserNane: RequestBody =
                customerUserNane.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val description: RequestBody =
                description.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            try {
                val dataAdd = userRepository.addNewYeuCauPhanLoai(
                    customerUserNane,
                    description,
                )
                NewYeuCauPhanLoai.value = dataAdd.data
                Log.e("apiNew", "--- " + dataAdd.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
                Log.e("apiNew", "lỗi trời quá đất ")
            }
        }
    }
}
