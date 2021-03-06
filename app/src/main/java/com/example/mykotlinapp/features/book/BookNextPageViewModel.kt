package com.example.mykotlinapp.features.book

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.AddressProvince
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

    val NewLich = MutableLiveData<NewLichThu>()
    val tinhtp = MutableLiveData<MutableList<AddressProvince>>()
    val huyenquan = MutableLiveData<MutableList<AddressProvince>>()
    val xaphuong = MutableLiveData<MutableList<AddressProvince>>()

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
        picture_2: MultipartBody.Part?,
        picture_3: MultipartBody.Part?,
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
                    picture_2!!,
                    picture_3!!,
                )
                NewLich.value = dataAdd.data
                Log.e("apiNew", "--- " + dataAdd.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
                Log.e("apiNew", "l???i tr???i qu?? ?????t ")
            }
        }
    }

    fun getTinh(ten: String){
        viewModelScope.launch {
            try {
                val data = userRepository.getTinh(ten).dataTinhtp!!
                tinhtp.value = data
            }catch (e: Exception){
                error.value = getErrorResponse(e)
            }
        }
    }

    fun getHuyen(tinh_id: Int) {
        viewModelScope.launch {
            try {
                val data = userRepository.getHuyen(tinh_id).dataTinhtp!!
                huyenquan.value = data
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
            }
        }
    }

    fun getXa(quan_id: Int) {
        viewModelScope.launch {
            try {
                val data = userRepository.getXa(quan_id).dataTinhtp!!
                xaphuong.value = data
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
            }
        }
    }
}