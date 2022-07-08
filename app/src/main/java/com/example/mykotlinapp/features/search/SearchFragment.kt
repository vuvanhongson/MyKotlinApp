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
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    val viewModel: SearchViewModel by viewModel()
    val inforViewModel : InformationViewModel by activityViewModels()
    private var adapterTinh: SearchAdapter? = null
    private lateinit var binding: FragmentSearchBinding
    var idapiQuan : Int = 0
    var idapiPhuong : Int = 0


    private fun registerLiveData() = with(viewModel) {
        getTinh("") //init from viewModel
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.d("data", " error ")
        }
        tinhtp.observe(viewLifecycleOwner) {
            adapterTinh = SearchAdapter(requireContext(), R.layout.list_item_dropdown, it)
            adapterTinh?.itemTinhClick = {
                binding.dropdownMenu.setText(it.ten)
                registerLiveDataQuan(it.id)
                idapiQuan = it.id
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

    private fun registerLiveDataQuan(id_tinh: Int) = with(viewModel) {
        getHuyen(id_tinh) //init from viewModel
        error.observe(viewLifecycleOwner) {
            Log.d("datahuyen", " error ")
        }
        huyenquan.observe(viewLifecycleOwner) {
            adapterTinh = SearchAdapter(requireContext(), R.layout.list_item_dropdown, it)
            adapterTinh?.itemTinhClick = {
                binding.dropdownMenuQuan.setText(it.ten)
                registerLiveDataPhuong(it.id)
                idapiPhuong = it.id
            }
            Log.d("datahuyen", " - $it")
            binding.dropdownMenuQuan.setAdapter(adapterTinh)
        }
    }

    private fun registerLiveDataPhuong(id_quan: Int) = with(viewModel) {
        getXa(id_quan) //init from viewModel
        error.observe(viewLifecycleOwner) {
            Log.d("dataxa", " error ")
        }
        xaphuong.observe(viewLifecycleOwner) {
            adapterTinh = SearchAdapter(requireContext(), R.layout.list_item_dropdown, it)
            adapterTinh?.itemTinhClick = {
                binding.dropdownMenuPhuong.setText(it.ten)
            }
            Log.d("dataxa", " - $it")
            binding.dropdownMenuPhuong.setAdapter(adapterTinh)
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
        binding.dropdownMenu.setOnClickListener {
            registerLiveData()
            binding.dropdownMenuQuan.setText("")
            binding.dropdownMenuPhuong.setText("")
        }
        binding.dropdownMenuQuan.setOnClickListener {
            if(idapiQuan != 0){
                registerLiveDataQuan(idapiQuan)
                binding.dropdownMenuPhuong.setText("")
            }else{
                Snackbar.make(binding.btnKqSearch, "Vui lòng chọn Tỉnh/Thành phố!", Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.dropdownMenuPhuong.setOnClickListener {
            if(idapiPhuong != 0){
                registerLiveDataPhuong(idapiPhuong)
            }else{
                Snackbar.make(binding.btnKqSearch, "Vui lòng chọn Huyện/Quận", Snackbar.LENGTH_SHORT).show()
            }
        }

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

        binding.radioButtonMaKH.setOnCheckedChangeListener { _, _ ->
            binding.edtSearch.hint = "Địa chỉ"
            binding.inputLayout.visibility = View.VISIBLE
            binding.inputLayoutQuan.visibility = View.VISIBLE
            binding.inputLayoutPhuong.visibility = View.VISIBLE
            onHideSoftKeyBoard()
        }
        binding.radioButtonDiachi.setOnCheckedChangeListener { _, _ ->
            binding.edtSearch.hint = "Mã khách hàng"
            binding.inputLayout.visibility = View.GONE
            binding.inputLayoutQuan.visibility = View.GONE
            binding.inputLayoutPhuong.visibility = View.GONE
            onHideSoftKeyBoard()
        }
    }

    companion object {
        fun newInstance() = SearchFragment().apply {
            arguments = bundleOf()
        }
    }
}