package com.example.mykotlinapp.features.slide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPageAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OnBoardingFragment1()
            1 -> OnBoardingFragment2()
            2 -> OnBoardingFragment3()
            else -> OnBoardingFragment1()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}