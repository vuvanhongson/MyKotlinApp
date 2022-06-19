package com.example.mykotlinapp.features.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentBookNextPageBinding
import com.example.mykotlinapp.databinding.FragmentSupportBinding

class BookNextPageFragment : Fragment() {

    private lateinit var binding: FragmentBookNextPageBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookNextPageBinding.inflate(inflater)

        binding.ivBackBookNextPage.setOnClickListener{
            requireActivity().onBackPressed()
        }

        return binding.root
    }

}