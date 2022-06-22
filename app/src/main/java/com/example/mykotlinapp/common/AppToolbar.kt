package com.example.mykotlinapp.common


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.LayoutViewToolbarBinding

class AppToolbar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs){

    private val binding: LayoutViewToolbarBinding
    private var showInfoButton = false
    private var showQRcodeButton = false
    private var showNotifiButton = false
    private var backgroundRes = 0
    private var QRcodeColor = 0
    private var NotifiColor = 0

    private fun init(attrs: AttributeSet?)
    {
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
        addView(binding.root)
        init(attrs)
    }

}
