package com.example.mykotlinapp.features.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentRequestUpdateBinding
import com.example.mykotlinapp.databinding.FragmentSearchBinding

class RequestUpdateFragment : Fragment() {

    private lateinit var binding: FragmentRequestUpdateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestUpdateBinding.inflate(inflater)

        binding.ivBackRequestUpdate.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }
}