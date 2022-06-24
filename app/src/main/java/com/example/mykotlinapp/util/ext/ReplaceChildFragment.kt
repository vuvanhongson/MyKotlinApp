package com.example.mykotlinapp.util.ext

import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R

fun Fragment.replaceChildFragment(layout: Int, fragment: Fragment) {
    activity?.supportFragmentManager?.beginTransaction()?.apply {
        setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
        addToBackStack(fragment::class.java.simpleName)
        replace(layout, fragment)
        commit()
    }
}