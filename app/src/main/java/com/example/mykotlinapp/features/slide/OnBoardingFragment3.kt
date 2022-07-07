package com.example.mykotlinapp.features.slide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentOnboarding3Binding
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.search.SearchFragment
import com.example.mykotlinapp.util.ext.addFragment


class OnBoardingFragment3 : Fragment() {

    private lateinit var binding: FragmentOnboarding3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboarding3Binding.inflate(inflater)
        binding!!.btnSearch.setOnClickListener {
            addFragment(R.id.container, SearchFragment.newInstance())
        }
        binding.book.setOnClickListener {
            addFragment(R.id.container, BookFragment.newInstance())
        }
        return binding.root
    }
}