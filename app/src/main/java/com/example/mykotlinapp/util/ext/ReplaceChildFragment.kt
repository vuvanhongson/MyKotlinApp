package com.example.mykotlinapp.util.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.R

fun Fragment.replaceChildFragment(fragmentManager: FragmentManager, fragment: Fragment) {
    activity?.supportFragmentManager?.beginTransaction()?.apply {
        setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
        addToBackStack(fragment::class.java.simpleName)
        hide(MainActivity.mCurrentTabActive!!)
        show(fragment)
//        replace(layout, fragment)
        commit()
    }
}