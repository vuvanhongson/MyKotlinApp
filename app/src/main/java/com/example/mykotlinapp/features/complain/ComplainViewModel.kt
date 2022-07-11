package com.example.mykotlinapp.features.complain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.Complain
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.Exception

class ComplainViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val NewComplain = MutableLiveData<Complain>()

    fun addNewKhieuNaiPhanAnh(
        customerUserNane: String,
        customerPhone: String,
        customerEmail: String,
        description: String,
        categoryPhanAnhKhieuNai: String,
        picture_1: MultipartBody.Part?,
        picture_2: MultipartBody.Part?,
        picture_3: MultipartBody.Part?,
    ) {
        viewModelScope.launch {
            val customerUserNane: RequestBody =
                customerUserNane.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val customerPhone: RequestBody =
                customerPhone.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val customerEmail: RequestBody =
                customerEmail.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val description: RequestBody =
                description.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val categoryPhanAnhKhieuNai: RequestBody =
                categoryPhanAnhKhieuNai.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            try {
                val dataAdd = userRepository.addNewKhieuNaiPhanAnh(
                    customerUserNane,
                    customerPhone,
                    customerEmail,
                    description,
                    categoryPhanAnhKhieuNai,
                    picture_1!!,
                    picture_2!!,
                    picture_3!!,
                )
                NewComplain.value = dataAdd.data
                Log.e("apiNew", "--- " + dataAdd.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
                Log.e("apiNew", "lỗi trời quá đất ")
            }
        }
    }
}
