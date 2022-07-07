package com.example.mykotlinapp.common

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.listener.SearchOnClickListener
import com.example.mykotlinapp.common.listener.ToolbarOnClickListenner
import com.example.mykotlinapp.databinding.LayoutViewToolbarBinding

class AppToolbar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs),
    ToolbarOnClickListenner {

    private val binding: LayoutViewToolbarBinding
    private var mSearchOnClickListener: SearchOnClickListener? = null
    private var showInfoButton = false
    private var showQRcodeButton = false
    private var showNotifiButton = false
    private var backgroundRes = 0
    private var QRcodeColor = 0
    private var NotifiColor = 0

    private fun init(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppToolbar)
        backgroundRes = typedArray.getResourceId(
            R.styleable.AppToolbar_toolBarBackground,
            R.color.color_00000000
        )
        typedArray.recycle()
        fillInfo()
    }

    private fun fillInfo() {

        binding.layoutToolbarContainer.setBackgroundResource(backgroundRes)
    }

//    fun changeColorIcon(iv1: Int)
//    {
//        if(iv1 == 0) {
//            binding.codeqr.setImageResource(R.drawable.ic_baseline_qr_code_24)
//            binding.notifi.setImageResource(R.drawable.ic_baseline_notifications_none_24)
//        }
//        else
//        {
//            binding.codeqr.setImageResource(R.drawable.ic_baseline_qr_code_white_24)
//            binding.notifi.setImageResource(R.drawable.ic_baseline_notifications_none_white_24)
//        }
//    }


    init {
        val inflater = LayoutInflater.from(context)
        binding = LayoutViewToolbarBinding.inflate(inflater)
        binding.toolbar = this
        addView(binding.root)
        init(attrs)

//        binding.textView .setText("KH-CIQ300004143")

        binding.textView.setOnEditorActionListener() { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    doSomething()
                    true
                }
                EditorInfo.IME_ACTION_NEXT -> {
                    doSomething()
                    true
                }
                KeyEvent.ACTION_DOWN -> {
                    doSomething()
                    true
                }
                KeyEvent.KEYCODE_ENTER -> {
                    doSomething()
                    true
                }
                else -> false
            }

        }
    }

    fun setOnSearchListener(searchOnClickListener: SearchOnClickListener?) {
        mSearchOnClickListener = searchOnClickListener
    }

    override fun UserOnClick() {
        ShowDialog().showDialog(context)
    }

    override fun CodeQROnClick() {
        ShowDialog().showDialog(context)
    }

    override fun NotiOnClick() {
        ShowDialog().showDialog(context)
    }

    fun doSomething() {
        Log.d("search", " " + binding.textView.text)
        mSearchOnClickListener!!.onSearchClicked(binding.textView.text.toString())
    }

}
