package com.example.mykotlinapp.util.customView

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.res.ResourcesCompat
import com.example.mykotlinapp.R
import kotlinx.android.synthetic.main.progress_dialog.view.*

class ProgressDialog(private val context: Context) {

    lateinit var dialog: CustomDialog

    fun dismiss() {
        dialog.dismiss()
    }

    fun show(): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.progress_dialog, null)

        view.cardView.setCardBackgroundColor(Color.parseColor("#26000000"))
        setColorFilter(
            view.progressBar.indeterminateDrawable,
            ResourcesCompat.getColor(context.resources, R.color.grac_green, null)
        )

        dialog = CustomDialog(context).apply {
            setContentView(view)
            show()
        }
        return dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            window?.decorView?.rootView?.setBackgroundResource(android.R.color.transparent)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}
