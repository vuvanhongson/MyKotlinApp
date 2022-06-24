package com.example.mykotlinapp.features

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mykotlinapp.features.home.HomeFragment

object CurrentTabActive {
    var CURRENTTAB : Fragment? = HomeFragment()
    var FRAM: FragmentManager? = null
}