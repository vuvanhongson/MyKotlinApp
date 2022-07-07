package com.example.mykotlinapp.features.search

import androidx.lifecycle.MutableLiveData
import com.example.mykotlinapp.data.model.SearchByLoginID
import com.example.mykotlinapp.util.base.BaseViewModel

class InformationViewModel () : BaseViewModel() {

    var loginID = MutableLiveData<MutableList<SearchByLoginID>>()


    fun getLoginID(loginid: MutableList<SearchByLoginID>) {
        loginID = MutableLiveData<MutableList<SearchByLoginID>>()
        loginID.value = loginid
    }
}