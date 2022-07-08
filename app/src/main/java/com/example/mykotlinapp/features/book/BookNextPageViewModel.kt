package com.example.mykotlinapp.features.book

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.NewLichThu
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Part
import java.lang.Exception

class BookNextPageViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val NewLich = MutableLiveData<MutableList<NewLichThu>>()

    fun addNewLichThu(
        customerUserNane: String,
        tinhThanhPhoId: String,
        quanHuyenId: String,
        xaPhuongId: String,
        shortAddress: String,
        khoiLuongThuGom: String,
        loaiDanhMucRac: String,
        idDanhMucRac: String,
        thoiGianThuGom: String,
        customerPhone: String,
        description: String,
        picture_1: MultipartBody.Part?,
    ) {
        viewModelScope.launch {
            val customerUserNane: RequestBody = customerUserNane.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val tinhThanhPhoId: RequestBody = tinhThanhPhoId.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val quanHuyenId: RequestBody = quanHuyenId.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val xaPhuongId: RequestBody = xaPhuongId.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val shortAddress: RequestBody = shortAddress.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val khoiLuongThuGom: RequestBody = khoiLuongThuGom.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val loaiDanhMucRac: RequestBody = loaiDanhMucRac.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val idDanhMucRac: RequestBody = idDanhMucRac.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val thoiGianThuGom: RequestBody = thoiGianThuGom.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val customerPhone: RequestBody = customerPhone.toRequestBody("multipart/formdata".toMediaTypeOrNull())
            val description: RequestBody = description.toRequestBody("multipart/formdata".toMediaTypeOrNull())

            try {
                val dataAdd = userRepository.addNewLichThuGomRac(
                    customerUserNane,
                    tinhThanhPhoId,
                    quanHuyenId,
                    xaPhuongId,
                    shortAddress,
                    khoiLuongThuGom,
                    loaiDanhMucRac,
                    idDanhMucRac,
                    thoiGianThuGom,
                    customerPhone,
                    description,
                    picture_1!!,
                )
                NewLich.value = dataAdd.data!!
                Log.e("urlReal", "--- " + dataAdd.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
                Log.e("urlReal", "lỗi trời quá đất ")
            }
        }
    }
}