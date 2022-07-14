package com.example.mykotlinapp.common

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.mykotlinapp.common.listener.BottomBarItemHandler
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.LayoutViewBottomBarBinding

class BottomBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs),
    BottomBarItemHandler {


    fun setOnBottomItemChangedListener(bottomItemChangedListener: BottomItemChangedListener?) {
        mBottomItemChangedListener = bottomItemChangedListener
    }

    override fun onHomeClicked() {
        if (!isHomeClick) {
            isHomeClick = true
            isBookingClick = false
            isSupportClick = false
            isQuestionlick = false
            isWalletClick = false
            binding.itemHome.setCheck(true)
            binding.itemSupport.setCheck(false)
            binding.itemBooking.setCheck(false)
            binding.itemQuestion.setCheck(false)
            binding.itemWallet.setCheck(false)
            mBottomItemChangedListener!!.onBottomItemClicked(HOME)
        }
    }

    override fun onSupportClicked() {
        if (!isSupportClick) {
            isHomeClick = false
            isBookingClick = false
            isSupportClick = true
            isQuestionlick = false
            isWalletClick = false
            binding.itemHome.setCheck(false)
            binding.itemSupport.setCheck(true)
            binding.itemBooking.setCheck(false)
            binding.itemQuestion.setCheck(false)
            binding.itemWallet.setCheck(false)
            mBottomItemChangedListener!!.onBottomItemClicked(SUPPORT)
        }
    }

    override fun onBookingClicked() {
        if (!isBookingClick) {
            isHomeClick = false
            isBookingClick = true
            isSupportClick = false
            isQuestionlick = false
            isWalletClick = false
            binding.itemHome.setCheck(false)
            binding.itemSupport.setCheck(false)
            binding.itemBooking.setCheck(true)
            binding.itemQuestion.setCheck(false)
            binding.itemWallet.setCheck(false)
            mBottomItemChangedListener!!.onBottomItemClicked(BOOKING)
        }
    }

    override fun onQuestionClicked() {
        if (!isQuestionlick) {
            isHomeClick = false
            isBookingClick = false
            isSupportClick = false
            isQuestionlick = true
            isWalletClick = false
            binding.itemHome.setCheck(false)
            binding.itemSupport.setCheck(false)
            binding.itemBooking.setCheck(false)
            binding.itemQuestion.setCheck(true)
            binding.itemWallet.setCheck(false)
            mBottomItemChangedListener!!.onBottomItemClicked(SEARCH)
        }
    }

    override fun onWalletClicked() {
        if (!isWalletClick) {
//            isHomeClick = false
//            isBookingClick = false
//            isSupportClick = false
//            isQuestionlick = false
//            isWalletClick = true
//            binding.itemHome.setCheck(false)
//            binding.itemSupport.setCheck(false)
//            binding.itemBooking.setCheck(false)
//            binding.itemQuestion.setCheck(false)
//            binding.itemWallet.setCheck(true)
//            mBottomItemChangedListener!!.onBottomItemClicked(WALLET)

            isHomeClick = true
            isBookingClick = false
            isSupportClick = false
            isQuestionlick = false
            isWalletClick = false
            binding.itemHome.setCheck(true)
            binding.itemSupport.setCheck(false)
            binding.itemBooking.setCheck(false)
            binding.itemQuestion.setCheck(false)
            binding.itemWallet.setCheck(false)
            mBottomItemChangedListener!!.onBottomItemClicked(HOME)
            ShowDialog().showDialog(context)
        }
    }

    companion object {
        const val HOME = "HOME"
        const val SUPPORT = "SUPPORT"
        const val BOOKING = "BOOKING"
        const val SEARCH = "SEARCH"
        const val WALLET = "WALLET"
        @SuppressLint("StaticFieldLeak")
        lateinit var binding: LayoutViewBottomBarBinding
        var mBottomItemChangedListener: BottomItemChangedListener? = null
        var isBookingClick = false
        var isHomeClick = false
        var isSupportClick = false
        var isQuestionlick = false
        var isWalletClick = false

        fun initControls() {
            binding.itemHome.init(HOME)
            binding.itemSupport.init(SUPPORT)
            binding.itemBooking.init(BOOKING)
            binding.itemQuestion.init(SEARCH)
            binding.itemWallet.init(WALLET)
            binding.itemHome.setCheck(true)
        }
        fun backHome()
        {
            isHomeClick = true
            isBookingClick = false
            isSupportClick = false
            isQuestionlick = false
            isWalletClick = false
            binding.itemHome.setCheck(true)
            binding.itemSupport.setCheck(false)
            binding.itemBooking.setCheck(false)
            binding.itemQuestion.setCheck(false)
            binding.itemWallet.setCheck(false)
//            mBottomItemChangedListener!!.onBottomItemClicked(HOME)
        }

        fun backBook()
        {
            isHomeClick = false
            isBookingClick = true
            isSupportClick = false
            isQuestionlick = false
            isWalletClick = false
            binding.itemHome.setCheck(false)
            binding.itemSupport.setCheck(false)
            binding.itemBooking.setCheck(true)
            binding.itemQuestion.setCheck(false)
            binding.itemWallet.setCheck(false)
//            mBottomItemChangedListener!!.onBottomItemClicked(BOOKING)
        }

        fun backSearch()
        {
            isHomeClick = false
            isBookingClick = false
            isSupportClick = false
            isQuestionlick = true
            isWalletClick = false
            binding.itemHome.setCheck(false)
            binding.itemSupport.setCheck(false)
            binding.itemBooking.setCheck(false)
            binding.itemQuestion.setCheck(true)
            binding.itemWallet.setCheck(false)
//            mBottomItemChangedListener!!.onBottomItemClicked(SEARCH)
        }
        fun backSupport()
        {
            isHomeClick = false
            isBookingClick = false
            isSupportClick = true
            isQuestionlick = false
            isWalletClick = false
            binding.itemHome.setCheck(false)
            binding.itemSupport.setCheck(true)
            binding.itemBooking.setCheck(false)
            binding.itemQuestion.setCheck(false)
            binding.itemWallet.setCheck(false)
//            mBottomItemChangedListener!!.onBottomItemClicked(SUPPORT)
        }

    }

    init {
        val inflater = LayoutInflater.from(context)
        binding = LayoutViewBottomBarBinding.inflate(inflater)
        binding.handler = this
        addView(binding.root)
        initControls()
    }

}