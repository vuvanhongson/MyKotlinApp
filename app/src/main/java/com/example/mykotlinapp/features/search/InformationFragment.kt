package com.example.mykotlinapp.features.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.FragmentInformationBinding
import com.example.mykotlinapp.features.question.QuestionWebViewActivity
import com.example.mykotlinapp.util.base.BaseFragment
import com.example.mykotlinapp.util.ext.addFragment
import com.google.android.material.snackbar.Snackbar

class InformationFragment : BaseFragment() {
    val viewModel: InformationViewModel by activityViewModels()
    private lateinit var binding: FragmentInformationBinding
    var  makh : String = ""

    private fun SearchByLoginID() {
//        viewModel.getLoginID(code)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.toolbarContact, it.toString(), Snackbar.LENGTH_SHORT).show()
        }
        viewModel.loginID.observe(viewLifecycleOwner) {
            Log.d("data_searchid", " - " + it.toString())
            var khachhang = it[0]
            binding.tvMakh.setText(khachhang.ma_khach_hang)
            binding.tvTenkh.setText(khachhang.ten_khach_hang)
            binding.tvDiachi.setText(khachhang.dia_chi)
            binding.tvMahd.setText(khachhang.ma_hop_dong)
            binding.tvDvdangky.setText(khachhang.ma_dich_vu)
            binding.tvVnthugom.setText(khachhang.ten_nhan_vien_thu_tien)
            binding.tvSdtVnthugom.setText(khachhang.sdt_nhan_vien_thu_tien)
            binding.tvKythanhtoan.setText(khachhang.ky_thanh_toan)
            binding.tvTienvanchuyen.setText(khachhang.tien_van_chuyen.toString())
            binding.tvTienthugom.setText(khachhang.tien_thu_gom.toString())
            binding.tvTienxuly.setText(khachhang.tien_xu_ly.toString())
            binding.tvThue.setText(khachhang.thue.toString())
            binding.tvGdmomo.setText(khachhang.fee_momo.toString())
            binding.tvTongtien.setText(khachhang.tong_so_tien_can_thanh_toan.toString())


            if(khachhang.is_phan_loai_rac_nguon == 0) {
                binding.layoutBtnDiaDiemPhanLoai.visibility = View.GONE
                binding.btnYeuCauCapNhat.visibility = View.VISIBLE
            }
            else {
                binding.layoutBtnDiaDiemPhanLoai.visibility = View.VISIBLE
                binding.btnYeuCauCapNhat.visibility = View.GONE
            }

            if(khachhang.trang_thai_thanh_toan != 0)
            {
                binding.tbThanhtoamMomo.visibility = View.VISIBLE
                binding.tbThanhtoanViettel.visibility = View.VISIBLE
                binding.btGbTienrac.visibility = View.VISIBLE
            }
            else
            {
                binding.tbThanhtoamMomo.visibility = View.GONE
                binding.tbThanhtoanViettel.visibility = View.GONE
                binding.btGbTienrac.visibility = View.GONE
            }

            makh = khachhang.ma_khach_hang.replace("-", "")

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationBinding.inflate(layoutInflater)

//        SearchByLoginID("KH-CIQ300004143")

        binding.btnYeuCauCapNhat.setOnClickListener {
            addFragment(R.id.container, RequestUpdateFragment.newInstance())
        }

        binding.question.setOnClickListener {
            ShowDialog().showDialog(requireContext())
        }
        binding.backInformation.setOnClickListener {
            requireActivity().onBackPressed()
        }

        SearchByLoginID()

        binding.btGbTienrac.setOnClickListener {
            Log.d("makh", makh + " ")
            val intent = Intent(activity, QuestionWebViewActivity::class.java)
            intent.putExtra(
                "urlquestion",
                "https://grac.vn/in-giay-bao-tien-rac/?code=" + makh
            )
            startActivity(intent)
        }
        return binding.root
    }

    companion object {
        fun newInstance() = InformationFragment().apply {
            arguments = bundleOf()
        }
    }
}