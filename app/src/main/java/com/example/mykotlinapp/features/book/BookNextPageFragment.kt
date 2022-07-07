package com.example.mykotlinapp.features.book

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.FragmentBookNextPageBinding
import com.example.mykotlinapp.features.book.adapter.PhotoBookAdapter
import com.example.mykotlinapp.util.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import gun0912.tedbottompicker.TedBottomPicker
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookNextPageFragment : BaseFragment() {

    val viewModel: BookNextPageViewModel by viewModel()

    private lateinit var binding: FragmentBookNextPageBinding
    var name: String? = ""
    private var photoBookAdapter: PhotoBookAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookNextPageBinding.inflate(inflater)

        //set title toolbar
        val args = this.arguments
        val inputData = args?.get("data")
        binding.tvToolbar.text = inputData.toString().toUpperCase()

        //set adapter
        photoBookAdapter = PhotoBookAdapter(requireActivity())
        val gridLayoutManager =
            GridLayoutManager(requireActivity(), 1, LinearLayoutManager.HORIZONTAL, false)

        //set recyclerView
        binding.recyclerviewPhotoBook.layoutManager = gridLayoutManager
        binding.recyclerviewPhotoBook.isFocusable = false
        binding.recyclerviewPhotoBook.isNestedScrollingEnabled = false
        binding.recyclerviewPhotoBook.adapter = photoBookAdapter

        registerLiveData()
        event()

        return binding.root
    }

    private fun registerLiveData() = with(viewModel) {
        NewLich.observe(viewLifecycleOwner) {
            progressDialog.dismiss()

        }
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Snackbar.make(binding.toolbarSupport, it.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun event() {
        binding.ivBackBookNextPage.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.question.setOnClickListener {
            ShowDialog().showDialog(requireActivity())
        }
        binding.imgCameraSelect.setOnClickListener {
            checkPermissionForImage()
            binding.imgCameraSelect.visibility = View.GONE
//            binding.imgAddImg1.visibility = View.VISIBLE
//            binding.imgAddImg2.visibility = View.VISIBLE
//            binding.imgAddImg3.visibility = View.VISIBLE
        }
        binding.imgAddImg1.setOnClickListener {
            checkPermissionForImage()
        }
        binding.imgAddImg2.setOnClickListener {
            checkPermissionForImage()
        }
        binding.imgAddImg3.setOnClickListener {
            checkPermissionForImage()
        }
        binding.edtImg.setOnClickListener {
            binding.imgCameraSelect.visibility = View.GONE
//            binding.imgAddImg1.visibility = View.VISIBLE
//            binding.imgAddImg2.visibility = View.VISIBLE
//            binding.imgAddImg3.visibility = View.VISIBLE
            checkPermissionForImage()
        }

        binding.btnAddThuGomRac.setOnClickListener {
            onHideSoftKeyBoard()
            progressDialog.show()
            viewModel.addNewLichThu(
                "KH-CIQ300004143",
                3,
                3,
                3,
                "1080 Quang Trung",
                100,
                3,
                3,
                "07-07-2022",
                "0909123456",
                "Mo ta chi tiết đặt lịch thu gom rác",
                null,
                null,
                null
            )
        }
    }

    @SuppressLint("WrongConstant")
    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission, IMAGE_REQUEST_CODE)
                requestPermissions(permissionCoarse, IMAGE_REQUEST_CODE)
            } else {
//                pickImageFromGallery()
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        val permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                openBottomPicker()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(
                    requireActivity(), "Permission Denied\n$deniedPermissions", Toast.LENGTH_SHORT
                ).show()
            }
        }
        TedPermission.with(requireActivity())
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("Vui lòng cấp quyền cho ứng dụng!")
            .setPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .check()
    }

    private fun openBottomPicker() {
        val listener = TedBottomPicker.OnMultiImageSelectedListener { uriList ->
            photoBookAdapter!!.setData(uriList)
        }
        val tedBottomPicker = TedBottomPicker.Builder(requireActivity())
            .setOnMultiImageSelectedListener(listener)
            .setCompleteButtonText("XONG")
            .setEmptySelectionText("Bạn chưa chọn ảnh!")
            .setTitle("Chọn tối đa 3 ảnh!")
            .setSelectMinCount(1)
            .setSelectMinCountErrorText("Bạn phải chọn tối thiểu 1 ảnh!")
            .setSelectMaxCount(3)
            .setSelectMaxCountErrorText("Bạn chỉ có thể chọn tối đa 3 ảnh!")
            .create()
        tedBottomPicker.show(requireActivity().supportFragmentManager)
    }


    //    private fun pickImageFromGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, IMAGE_REQUEST_CODE) // GIVE AN INTEGER VALUE FOR IMAGE_PICK_CODE LIKE 1000
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST_CODE) {
//            // I'M GETTING THE URI OF THE IMAGE AS DATA AND SETTING IT TO THE IMAGEVIEW
//            binding.imgCameraSelect.setImageURI(data?.data)
//        }
//    }
//
    companion object {
        val IMAGE_REQUEST_CODE = 100
        fun newInstance() = BookNextPageFragment().apply {
            arguments = bundleOf()
        }
    }
}