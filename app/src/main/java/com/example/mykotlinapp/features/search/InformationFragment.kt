package com.example.mykotlinapp.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentInformationBinding
import com.example.mykotlinapp.databinding.FragmentSearchBinding

class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationBinding.inflate(layoutInflater)

        binding!!.btnYeuCauCapNhat.setOnClickListener {
            val frament: Fragment = RequestUpdateFragment()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.container, frament)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.backInformation.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }
}