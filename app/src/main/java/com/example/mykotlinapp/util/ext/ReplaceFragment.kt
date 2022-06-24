package com.example.mykotlinapp.util.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(layout: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(layout, fragment)
        .commit()
}
