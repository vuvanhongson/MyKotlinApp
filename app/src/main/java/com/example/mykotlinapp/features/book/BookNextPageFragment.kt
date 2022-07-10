package com.example.mykotlinapp.features.book

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
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
import com.example.mykotlinapp.util.ext.ImageResizer
import com.example.mykotlinapp.util.ext.RealPathUtil
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import gun0912.tedbottompicker.TedBottomPicker
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class BookNextPageFragment : BaseFragment() {

    val viewModel: BookNextPageViewModel by viewModel()
    private var mListPhotos: ArrayList<Uri>? = null

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
            Log.e("urlReal", " thanhf coong")
        }
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.e("urlReal", " thaast baij")
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

            var photo1: MultipartBody.Part? = null
            var photo2: MultipartBody.Part? = null
            var photo3: MultipartBody.Part? = null

//            if (mListPhotos!!.size > 0)
//                for (i in 0..mListPhotos!!.size - 1) {
//                    var uri = mListPhotos!![1]
//                    var strRealPath: String? = RealPathUtil.getRealPath(requireContext(), uri)
//                    Log.e("urlReal", " " + i + "---- " +  strRealPath)
////            var file : File = File(strRealPath)
//                    var fullSizeBitmap = BitmapFactory.decodeFile(strRealPath)
//                    var reduceBitmap = ImageResizer.reduceBitmapSize(fullSizeBitmap, 250000)
//                    var reducedFile = getBitmapFile(reduceBitmap)
//
//                    var image = reducedFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//                    Log.e("urlReal", "reducedFile " + i + "---- " +  reducedFile.name)
//                    if (i == 0) {
//                        photo1 =  MultipartBody.Part.createFormData("picture_1",  "_1" + reducedFile.name , image)
//                    }
//                    if (i == 1) {
//                        photo2 =
//                            MultipartBody.Part.createFormData("picture_2","_2" + reducedFile.name , image)
//                    }
//                    if (i == 2) {
//                        photo3 =
//                            MultipartBody.Part.createFormData("picture_3", "_3" + reducedFile.name , image)
//                    }
//                }

            val uri1 = mListPhotos!![0]
            var strRealPath1: String? = RealPathUtil.getRealPath(requireContext(), uri1)
            Log.e("urlReal", "---- " + strRealPath1)
//            var file : File = File(strRealPath)
            var fullSizeBitmap1 = BitmapFactory.decodeFile(strRealPath1)
            var reduceBitmap1 = ImageResizer.reduceBitmapSize(fullSizeBitmap1, 250000)
            var reducedFile1 = getBitmapFile(reduceBitmap1)
            val image1 = reducedFile1.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            photo1 = MultipartBody.Part.createFormData("picture_1", "_1" +  reducedFile1.name, image1)


            val uri2 = mListPhotos!![1]
            var strRealPath2: String? = RealPathUtil.getRealPath(requireContext(), uri2)
            Log.e("urlReal", "---- " + strRealPath2)
//            var file : File = File(strRealPath)
            var fullSizeBitmap2 = BitmapFactory.decodeFile(strRealPath2)
            var reduceBitmap2 = ImageResizer.reduceBitmapSize(fullSizeBitmap2, 250000)
            var reducedFile2 = getBitmapFile(reduceBitmap2)
            val image2 = reducedFile2.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            photo2 = MultipartBody.Part.createFormData("picture_2","_2" +  reducedFile2.name, image2)


            val uri3 = mListPhotos!![2]
            var strRealPath3: String? = RealPathUtil.getRealPath(requireContext(), uri3)
            Log.e("urlReal", "---- " + strRealPath3)
//            var file : File = File(strRealPath)
            var fullSizeBitmap3 = BitmapFactory.decodeFile(strRealPath3)
            var reduceBitmap3 = ImageResizer.reduceBitmapSize(fullSizeBitmap3, 250000)
            var reducedFile3 = getBitmapFile(reduceBitmap3)
            val image3 = reducedFile3.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            photo3 = MultipartBody.Part.createFormData("picture_3", "_3" +  reducedFile3.name, image3)


            onHideSoftKeyBoard()
            progressDialog.show()
            viewModel.addNewLichThu(
                "KH-CIQ300004143",
                "3",
                "3",
                "3",
                "1080 Quang Trung",
                "100",
                "3",
                "3",
                "07-07-2022",
                "0909123456",
                "Mo ta chi tiết đặt lịch thu gom rác",
                photo1!!,
                photo2!!,
                photo3!!,
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
            mListPhotos = uriList
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

    private fun getBitmapFile(reduceBitmap: Bitmap): File {
        var file: File = File(
            Environment.getExternalStorageDirectory()
                .toString() + File.separator + "reduced_file.png"
        )
        var bos = ByteArrayOutputStream()
        reduceBitmap.compress(Bitmap.CompressFormat.PNG, 3, bos)
        var bitmapdata = bos.toByteArray()
        try {
            file.createNewFile()
            var fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            return file
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file
    }

    companion object {
        val IMAGE_REQUEST_CODE = 100
        fun newInstance() = BookNextPageFragment().apply {
            arguments = bundleOf()
        }
    }
}