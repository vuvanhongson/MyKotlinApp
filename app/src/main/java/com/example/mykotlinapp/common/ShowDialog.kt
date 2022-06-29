package com.example.mykotlinapp.common

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mykotlinapp.R

class ShowDialog{
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

}