package com.example.mykotlinapp.features.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapp.data.UserRepository
import com.example.mykotlinapp.data.model.AddressProvince
import com.example.mykotlinapp.data.model.SearchByLoginID
import com.example.mykotlinapp.util.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel(private val userRepository: UserRepository) : BaseViewModel(){

    val tinhtp = MutableLiveData<MutableList<AddressProvince>>()
    val huyenquan = MutableLiveData<MutableList<AddressProvince>>()
    val xaphuong = MutableLiveData<MutableList<AddressProvince>>()
    var loginID = MutableLiveData<MutableList<SearchByLoginID>>()
    var isSuccessSearch = MutableLiveData<Boolean>()
    var address = MutableLiveData<MutableList<SearchByLoginID>>()
    var isSuccessAddress = MutableLiveData<Boolean>()

    fun getTinh(ten: String){
        viewModelScope.launch {
            try {
                val data = userRepository.getTinh(ten).dataTinhtp!!
                tinhtp.value = data
                Log.d("api", " - " + data.toString())
            }catch (e: Exception){
                error.value = getErrorResponse(e)
                Log.d("api", " errorapi ")
            }
        }
    }

    fun getHuyen(tinh_id: Int) {
        viewModelScope.launch {
            try {
                val data = userRepository.getHuyen(tinh_id).dataTinhtp!!
                huyenquan.value = data
                Log.d("apihuyen", " - " + data.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
                Log.d("apihuyen", " errorapi ")
            }
        }
    }

    fun getXa(quan_id: Int) {
        viewModelScope.launch {
            try {
                val data = userRepository.getXa(quan_id).dataTinhtp!!
                xaphuong.value = data
                Log.d("apixa", " - " + data.toString())
            } catch (e: Exception) {
                error.value = getErrorResponse(e)
                Log.d("apixa", " errorapi ")
            }
        }
    }

    fun getLoginID(maKhachHang: String) {
        viewModelScope.launch {
            try {
                loginID = MutableLiveData<MutableList<SearchByLoginID>>()
                val data = userRepository.getDataLoginID(maKhachHang)
                if(data.code == 200)
                {
                    loginID.value = data.dataSearchLoginID!!
                    isSuccessSearch.value = true
                    Log.d("api_loginid", "$maKhachHang - $data")
                }
                else
                {
                    isSuccessSearch.value = false
                }

            } catch (e: Exception) {
                isSuccessSearch.value = false
                error.value = getErrorResponse(e)
            }
        }
    }

    fun getAddress(customerAddress: String) {
        viewModelScope.launch {
            try {
                address = MutableLiveData<MutableList<SearchByLoginID>>()
                val data = userRepository.getAddress(customerAddress)
                if(data.code == 200)
                {
                    address.value = data.dataSearchLoginID!!
                    isSuccessAddress.value = true
                    Log.d("api_address", "$customerAddress - $data")
                }
                else
                {
                    isSuccessAddress.value = false
                }

            } catch (e: Exception) {
                isSuccessAddress.value = false
                error.value = getErrorResponse(e)
            }
        }
    }
}