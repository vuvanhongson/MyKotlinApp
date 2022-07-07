package com.example.mykotlinapp.features.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.NewLichThu
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Part
import java.lang.Exception

class BookNextPageViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val NewLich = MutableLiveData<MutableList<NewLichThu>>()

    fun addNewLichThu(
        customerUserNane: String,
        tinhThanhPhoId: Int,
        quanHuyenId: Int,
        xaPhuongId: Int,
        shortAddress: String,
        khoiLuongThuGom: Int,
        categoryRacThaiDacBietId: Int,
        categoryDangKyThuRacId: Int,
        thoiGianThuGom: String,
        customerPhone: String,
        description: String,
        picture_1: Part?,
        picture_2: Part?,
        picture_3: Part?
    ) {
        viewModelScope.launch {
            try {
                val dataAdd = userRepository.addNewLichThuGomRac(
                    customerUserNane,
                    tinhThanhPhoId,
                    quanHuyenId,
                    xaPhuongId,
                    shortAddress,
                    khoiLuongThuGom,
                    categoryRacThaiDacBietId,
                    categoryDangKyThuRacId,
                    thoiGianThuGom,
                    customerPhone,
                    description,
                    picture_1,
                    picture_2,
                    picture_3
                )
                NewLich.value = dataAdd.data!!
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
            }
        }
    }
}