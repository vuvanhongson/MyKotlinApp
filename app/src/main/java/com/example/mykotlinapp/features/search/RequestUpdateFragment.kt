package com.example.mykotlinapp.features.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.FragmentRequestUpdateBinding
import com.example.mykotlinapp.features.complain.ComplainActivity
import com.example.mykotlinapp.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RequestUpdateFragment : BaseFragment() {

    val viewModel: RequestUpdateViewModel by viewModel()
    private lateinit var binding: FragmentRequestUpdateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestUpdateBinding.inflate(inflater)

        registerLiveData()
        event()

        return binding.root
    }

    private fun registerLiveData() {
        viewModel.NewYeuCauPhanLoai.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.e("urlReal", " thanhf coong")
            if (it.accept == true) {
                MainActivity.mInforfragment = InformationFragment()
                MainActivity.mRequestUpdate = RequestUpdateFragment()
                ShowDialog().showDialogAddNewTrue(requireActivity())
            } else
                ShowDialog().showDialogAddNewFalse(requireActivity())
        }
        viewModel.error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.e("urlReal", " thaast baij")
            ShowDialog().showDialogAddNewError(requireActivity())
        }
    }

    private fun event() {
        binding.ivBackRequestUpdate.setOnClickListener {
            onHideSoftKeyBoard()
            requireActivity().onBackPressed()
        }

        binding.btnComplainUpdate.setOnClickListener {
            onHideSoftKeyBoard()
            startActivity(Intent(activity, ComplainActivity::class.java))
        }

        binding.btnBookUpdate.setOnClickListener {
            onHideSoftKeyBoard()
            MainActivity.navigateFragment(MainActivity.mBook)
        }

        binding.ivBackRequestUpdate.setOnClickListener {
            MainActivity.mRequestUpdate = RequestUpdateFragment()
            MainActivity.navigateFragment(MainActivity.mInforfragment)
        }

        binding.ivQuestionToolbar.setOnClickListener {
            ShowDialog().showDialog(requireContext())
        }
        binding.btnSendUpdate.setOnClickListener {
            var makhachhang = binding.edtMakh.text.toString()
            var noidung = binding.editNoidung.text.toString()

            onHideSoftKeyBoard()
            if (makhachhang == "" || noidung == "") {
                ShowDialog().showDialogAddNewFalse(requireActivity())
            } else {
                progressDialog.show()
                viewModel.addNewYeuCauPhanLoai(
                    makhachhang,
                    noidung.toString(),
                )
            }
        }
    }

    companion object {
        fun newInstance() = RequestUpdateFragment().apply {
            arguments = bundleOf()
        }
    }
}