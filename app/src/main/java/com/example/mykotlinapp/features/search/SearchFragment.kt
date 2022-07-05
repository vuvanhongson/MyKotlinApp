package com.example.mykotlinapp.features.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.data.model.AddressProvince
import com.example.mykotlinapp.databinding.FragmentSearchBinding
import com.example.mykotlinapp.features.search.Adapter.SearchAdapter
import com.example.mykotlinapp.util.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    val viewModel: SearchViewModel by viewModel()
    private var adapterTinh: SearchAdapter? = null
    private lateinit var binding: FragmentSearchBinding

    private fun registerLiveData() = with(viewModel) {
        error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.toolbarContact, it.toString(), Snackbar.LENGTH_SHORT).show()
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        event()
        registerLiveData()
        return binding.root
    }

    fun event() {
        binding.backSearch.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.question.setOnClickListener {
            ShowDialog().showDialog(requireContext())
        }

        binding.btnKqSearch.setOnClickListener {
            onHideSoftKeyBoard()
            val frament: Fragment = InformationFragment()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.container, frament)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    companion object {
        fun newInstance() = SearchFragment().apply {
            arguments = bundleOf()
        }
    }
}