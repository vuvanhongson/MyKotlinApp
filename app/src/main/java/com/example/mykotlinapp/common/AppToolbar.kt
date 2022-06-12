package com.example.mykotlinapp.common


import android.content.Context
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

    private fun init(attrs: AttributeSet?)
    {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppToolbar)
        backgroundRes = typedArray.getResourceId(
            R.styleable.AppToolbar_toolBarBackground,
            R.color.color_00000000
        )
        typedArray.recycle()

    }

    init {
        val inflater = LayoutInflater.from(context)
        binding = LayoutViewToolbarBinding.inflate(inflater)
        addView(binding.root)
        init(attrs)
    }

}
