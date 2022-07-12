package com.example.mykotlinapp.features.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.FragmentRequestUpdateBinding
import com.example.mykotlinapp.databinding.FragmentSearchBinding
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.complain.ComplainActivity
import com.example.mykotlinapp.features.support.SupportFragment
import com.example.mykotlinapp.util.ext.addFragment

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

        binding.btnComplainUpdate.setOnClickListener {
            startActivity(Intent(activity, ComplainActivity::class.java))
        }

        binding.btnBookUpdate.setOnClickListener {
            addFragment(R.id.container, BookFragment.newInstance())
        }

        binding.ivBackRequestUpdate.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.ivQuestionToolbar.setOnClickListener {
            ShowDialog().showDialog(requireContext())
        }

        return binding.root
    }

    companion object {
        fun newInstance() = RequestUpdateFragment().apply {
            arguments = bundleOf()
        }
    }
}