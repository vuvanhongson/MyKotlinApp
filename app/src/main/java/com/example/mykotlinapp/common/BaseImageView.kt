package com.example.mykotlinapp.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class BaseImageView : AppCompatImageView {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
    }
}