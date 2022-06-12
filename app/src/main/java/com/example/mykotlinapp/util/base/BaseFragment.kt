package com.example.mykotlinapp.util.base

import android.app.Activity

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.util.customView.ProgressDialog

abstract class BaseFragment() : Fragment() {

    lateinit var progressDialog: ProgressDialog

    override fun onAttach(context : Context)
    {
        super.onAttach(context)
        progressDialog = ProgressDialog(context)
    }

    fun onHideSoftKeyBoard()
    {
        val inputMng : InputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMng.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

}