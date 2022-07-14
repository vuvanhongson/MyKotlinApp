package com.example.mykotlinapp.features.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.data.model.AddressProvince
import com.example.mykotlinapp.databinding.FragmentSearchBinding
import com.example.mykotlinapp.features.search.Adapter.SearchAdapter
import com.example.mykotlinapp.util.base.BaseFragment
import com.example.mykotlinapp.util.ext.addFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    val viewModel: SearchViewModel by viewModel()
    val inforViewModel: InformationViewModel by activityViewModels()
    private var adapterTinh: SearchAdapter? = null
    private lateinit var binding: FragmentSearchBinding
    var idapiQuan: Int = 0
    var idapiPhuong: Int = 0
    var tinh: String = ""
    var huyen: String = ""
    var xa: String = ""
    var diachi: String = ""

    private fun searchInformationByLoginId() = with(viewModel) {
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.d("loginID", "- Không tìm thấy id")
        }
        isSuccessSearch.observe(viewLifecycleOwner)
        {
            progressDialog.dismiss()
            if (it) {
                loginID.observe(viewLifecycleOwner) {
                    inforViewModel.getLoginID(it)
                    MainActivity.addNewFragment(MainActivity.mInforfragment)
                    val fragment = MainActivity.mInforfragment
                    MainActivity.navigateFragment(fragment)
                    Log.d("loginID", "- success ")
                }
            } else {
                Log.d("loginID", "- error ")
                ShowDialog().showDialogSearch(requireActivity())

            }
        }
    }

    private fun searchInformationByAddress() = with(viewModel){
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.d("address", "- không tìm thấy:" + diachi)
        }
        isSuccessAddress.observe(viewLifecycleOwner)
        {
            progressDialog.dismiss()
            if (it) {
                address.observe(viewLifecycleOwner) {
                    inforViewModel.getAddress(it)
                    MainActivity.addNewFragment(MainActivity.mInforfragment)
                    val fragment = MainActivity.mInforfragment
                    MainActivity.navigateFragment(fragment)
                    Log.d("address", diachi)
                }
            } else {
                Log.d("loginID", "- error Address")
                ShowDialog().showDialogSearch(requireActivity())

            }
        }
    }

    private fun registerLiveData() = with(viewModel) {
        getTinh("") //init from viewModel
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.d("data", " error ")
        }
        tinhtp.observe(viewLifecycleOwner) {
            it.remove(AddressProvince(50, "Hồ Chí Minh", "Thành phố Hồ Chí Minh"))
            it.add(0, AddressProvince(50, "Hồ Chí Minh", "Thành phố Hồ Chí Minh"))
            adapterTinh = SearchAdapter(requireContext(), R.layout.list_item_dropdown, it)
            adapterTinh?.itemTinhClick = {
                binding.dropdownMenu.setText(it.ten)
                registerLiveDataQuan(it.id)
                idapiQuan = it.id
                tinh = it.fullName
            }
            Log.d("datatinh", " - $it")
            binding.dropdownMenu.setAdapter(adapterTinh)
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
                binding.dropdownMenuQuan.setText(it.fullName)
                registerLiveDataPhuong(it.id)
                idapiPhuong = it.id
                huyen = it.fullName
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
                binding.dropdownMenuPhuong.setText(it.fullName)
                xa = it.fullName
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
        event()
        registerLiveData()
        searchInformationByLoginId()
        searchInformationByAddress()
        return binding.root
    }

    fun event() {

        binding.dropdownMenu.setOnClickListener {
            registerLiveData()
            binding.dropdownMenuQuan.setText("")
            binding.dropdownMenuPhuong.setText("")
        }
        binding.dropdownMenuQuan.setOnClickListener {
            if (idapiQuan != 0) {
                registerLiveDataQuan(idapiQuan)
                binding.dropdownMenuPhuong.setText("")
            } else {
                Snackbar.make(
                    binding.btnKqSearch,
                    "Vui lòng chọn Tỉnh/Thành phố!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        binding.dropdownMenuPhuong.setOnClickListener {
            if (idapiPhuong != 0) {
                registerLiveDataPhuong(idapiPhuong)
            } else {
                Snackbar.make(
                    binding.btnKqSearch,
                    "Vui lòng chọn Huyện/Quận",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.backSearch.setOnClickListener {
            onHideSoftKeyBoard()
            MainActivity.mSearch = SearchFragment()
            MainActivity.navigateFragment(MainActivity.mHomeFragment)
            MainActivity.backHome()
        }

        binding.question.setOnClickListener {
            ShowDialog().showDialog(requireContext())
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

        binding.btnKqSearch.setOnClickListener {
            onHideSoftKeyBoard()
            progressDialog.show()
            if (binding.radioButtonMaKH.isChecked) {
                viewModel.getLoginID(binding.edtSearch.text.toString().trim())
                Log.d("checksearch","- code")
            }
            if (!binding.radioButtonMaKH.isChecked){
                diachi = binding.edtSearch.text.toString().trim() + ", " + xa + ", "  + huyen + ", " + tinh
                viewModel.getAddress(diachi.trim())
                Log.d("checksearch","- diachi")
            }
        }
    }

    companion object {
        fun newInstance() = SearchFragment().apply {
            arguments = bundleOf()
        }
    }
}