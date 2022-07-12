package com.example.mykotlinapp.common

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.common.listener.dialogAddNewListener
import com.example.mykotlinapp.features.home.HomeFragment


class ShowDialog{
    private var mdialogAddNewListener: dialogAddNewListener? = null

   fun showDialog(context : Context) {
        val customDialog = Dialog(context)
        customDialog.setContentView(R.layout.dialog_updating)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val ivClose = customDialog.findViewById<ImageView>(R.id.iv_close)
        ivClose.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }
    fun showDialogSearch(context : Context) {
        val customDialog = Dialog(context)
        customDialog.setContentView(R.layout.dialog_not_found_search)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val ivClose = customDialog.findViewById<ImageView>(R.id.iv_close)
        ivClose.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    fun showDialogAddNewFalse(context : Context) {
        val customDialog = Dialog(context)
        customDialog.setContentView(R.layout.dialog_message_accept_false)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val tvOk = customDialog.findViewById<BaseTextView>(R.id.tv_ok_false)
        tvOk.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }


    fun showDialogAddNewTrue(context : Context) {
        val customDialog = Dialog(context)
        customDialog.setContentView(R.layout.dialog_message_accept_true)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val tvOk = customDialog.findViewById<BaseTextView>(R.id.tv_ok_true)
        tvOk.setOnClickListener {
            customDialog.dismiss()
            MainActivity.navigateFragment(MainActivity.mHomeFragment)

        }
        customDialog.show()
    }

    fun showDialogAddNewError(context : Context) {
        val customDialog = Dialog(context)
        customDialog.setContentView(R.layout.dialog_message_accept_error)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val tvOk = customDialog.findViewById<BaseTextView>(R.id.tv_ok_error)
        tvOk.setOnClickListener {
            customDialog.dismiss()
            mdialogAddNewListener!!.onOkClicked()
        }
        customDialog.show()
    }

    fun setOndialogAddNewListener(DialogAddNewListener: dialogAddNewListener?) {
        mdialogAddNewListener = DialogAddNewListener
    }
}