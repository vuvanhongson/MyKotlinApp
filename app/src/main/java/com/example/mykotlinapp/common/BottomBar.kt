package com.example.mykotlinapp.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.mykotlinapp.common.listener.BottomBarItemHandler
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.LayoutViewBottomBarBinding

class BottomBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs),
    BottomBarItemHandler {
    private val binding: LayoutViewBottomBarBinding
    private var mBottomItemChangedListener: BottomItemChangedListener? = null
    private var isBookingClick = false
    private var isHomeClick = false
    private var isSupportClick = false
    private var isQuestionlick = false
    private var isWalletClick = false
    private fun initControls() {
        binding.itemHome.init(HOME)
        binding.itemSupport.init(SUPPORT)
        binding.itemBooking.init(BOOKING)
        binding.itemQuestion.init(QUESTION)
        binding.itemWallet.init(WALLET)
        binding.itemHome.setCheck(true)
    }

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
            mBottomItemChangedListener!!.onBottomItemClicked(QUESTION)
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
        const val QUESTION = "QUESTION"
        const val WALLET = "WALLET"
    }

    init {
        val inflater = LayoutInflater.from(context)
        binding = LayoutViewBottomBarBinding.inflate(inflater)
        binding.handler = this
        addView(binding.root)
        initControls()
    }

}