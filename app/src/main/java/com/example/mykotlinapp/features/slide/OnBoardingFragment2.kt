package com.example.mykotlinapp.features.slide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.databinding.FragmentOnboarding2Binding

class OnBoardingFragment2 : Fragment() {
    private lateinit var binding: FragmentOnboarding2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboarding2Binding.inflate(inflater)
        binding!!.btnSearch.setOnClickListener {
            MainActivity.addNewFragment(MainActivity.mSearch)
            val fragment = MainActivity.mSearch
            MainActivity.navigateFragment(fragment)
        }
        binding.book.setOnClickListener {
            MainActivity.navigateFragment(MainActivity.mBook)
        }
        return binding.root
    }
}