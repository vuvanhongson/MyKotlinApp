package com.example.mykotlinapp.features.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.FragmentBookNextPageBinding

class BookNextPageFragment : Fragment() {

    private lateinit var binding: FragmentBookNextPageBinding
    var name : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookNextPageBinding.inflate(inflater)

        binding.ivBackBookNextPage.setOnClickListener {
            requireActivity().onBackPressed()
        }
        val args = this.arguments
        val inputData = args?.get("data")
        binding.tvToolbar.text = inputData.toString().toUpperCase()

        binding.question.setOnClickListener {
            ShowDialog().showDialog(requireActivity())
        }

        return binding.root
    }

    companion object {
        fun newInstance() = BookNextPageFragment().apply {
            arguments = bundleOf()
        }
    }



}