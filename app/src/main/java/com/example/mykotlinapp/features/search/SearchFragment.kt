package com.example.mykotlinapp.features.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.FragmentSearchBinding
import com.example.mykotlinapp.features.search.Adapter.SearchAdapter
import com.example.mykotlinapp.util.base.BaseFragment
import com.example.mykotlinapp.util.ext.addFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    val viewModel: SearchViewModel by viewModel()
    val inforViewModel : InformationViewModel by activityViewModels()
    private var adapterTinh: SearchAdapter? = null
    private lateinit var binding: FragmentSearchBinding


    private fun registerLiveData() = with(viewModel) {
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.d("data", " error ")
        }
        tinhtp.observe(viewLifecycleOwner) {
            adapterTinh = SearchAdapter(requireContext(), R.layout.list_item_dropdown, it)
//            it.add(AddressProvince())
            adapterTinh?.itemTinhClick = {
                binding.dropdownMenu.setText(it.ten)
            }
            Log.d("datatinh", " - " + it.toString())
            binding.dropdownMenu.setAdapter(adapterTinh)
        }

        isSuccess.observe(viewLifecycleOwner)
        {
            progressDialog.dismiss()
            if(it){
                loginID.observe(viewLifecycleOwner){
                    inforViewModel.getLoginID(it)
                    addFragment(R.id.container, InformationFragment.newInstance())

                }
            }
            else
            {
                ShowDialog().showDialogSearch(requireActivity())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)

        binding.edtSearch.setText("KH-CIQ300004143")

        event()
        registerLiveData()
        return binding.root
    }

    fun event() {
        binding.backSearch.setOnClickListener {
            onHideSoftKeyBoard()
            requireActivity().onBackPressed()
        }

        binding.question.setOnClickListener {
            ShowDialog().showDialog(requireContext())
        }

        binding.btnKqSearch.setOnClickListener {
            onHideSoftKeyBoard()
//            addFragment(R.id.container, InformationFragment.newInstance())
            progressDialog.show()
            viewModel.getLoginID(binding.edtSearch.text.toString())
        }
    }

    companion object {
        fun newInstance() = SearchFragment().apply {
            arguments = bundleOf()
        }
    }
}