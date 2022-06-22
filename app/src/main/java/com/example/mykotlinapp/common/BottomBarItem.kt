package com.example.mykotlinapp.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.LayoutViewBottomBarItemBinding
import okhttp3.internal.wait

class BottomBarItem(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val binding: LayoutViewBottomBarItemBinding
    private var mResChecked = 0
    private var mResUnchecked = 0
    private val mResTextColorChecked = R.color.grac_green
    private val mResTextColorUnCheck = R.color.grac_grey
    fun init(type: String?) {
        when (type) {
            BottomBar.HOME -> {
                mResChecked = R.drawable.ic_trangchu_green
                mResUnchecked = R.drawable.ic_trangchu_gray
                binding.tvItemTitle.text = resources.getString(R.string.bottom_bar_home_title)
                setCheck(false)
            }
            BottomBar.SUPPORT -> {
                mResChecked = R.drawable.ic_hotro_green
                mResUnchecked = R.drawable.ic_hotro_gray
                binding.tvItemTitle.text = resources.getString(R.string.bottom_bar_support_title)
                setCheck(false)
            }
            BottomBar.BOOKING -> {
                mResChecked = R.drawable.ic_datlich_green
                mResUnchecked = R.drawable.ic_datlich_gray
                binding.tvItemTitle.text = resources.getString(R.string.bottom_bar_booking_title)
                setCheck(false)
            }
            BottomBar.QUESTION -> {
                mResChecked = R.drawable.ic_cauhoi_green
                mResUnchecked = R.drawable.ic_cauhoi_gray
                binding.tvItemTitle.text = resources.getString(R.string.bottom_bar_question_title)
                setCheck(false)
            }
            BottomBar.WALLET -> {
                mResChecked = R.drawable.ic_vi_green
                mResUnchecked = R.drawable.ic_vi_gray
                binding.tvItemTitle.text = resources.getString(R.string.bottom_bar_wallet_title)
                setCheck(false)
            }
        }
    }

    fun setCheck(isChecked: Boolean) {
        binding.ivBottomBarItem.setImageResource(if (isChecked) mResChecked else mResUnchecked)
        binding.tvItemTitle.setTextColor(
            if (isChecked) resources.getColor(mResTextColorChecked) else resources.getColor(
                mResTextColorUnCheck
            )
        )
    }

    init {
        val inflater = LayoutInflater.from(context)
        binding = LayoutViewBottomBarItemBinding.inflate(inflater)
        addView(binding.root)
    }
}