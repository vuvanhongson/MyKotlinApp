package com.example.mykotlinapp.features.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.FragmentInformationBinding
import com.example.mykotlinapp.util.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class InformationFragment : BaseFragment() {
    val viewModel: InformationViewModel by viewModel()
    private lateinit var binding: FragmentInformationBinding

    private fun SearchByLoginID(code : String) {
        viewModel.getLoginID(code)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.toolbarContact, it.toString(), Snackbar.LENGTH_SHORT).show()
        }
        viewModel.loginID.observe(viewLifecycleOwner) {
            Log.d("data_searchid", " - " + it.toString())
            if (it != null) {

            }else{

            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationBinding.inflate(layoutInflater)

        SearchByLoginID("KH-CIQ300004143")

        binding.btnYeuCauCapNhat.setOnClickListener {
            val frament: Fragment = RequestUpdateFragment()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.container, frament)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.question.setOnClickListener {
            ShowDialog().showDialog(requireContext())
        }
        binding.backInformation.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }
}