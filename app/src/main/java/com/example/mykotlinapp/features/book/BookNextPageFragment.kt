package com.example.mykotlinapp.features.book

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.common.listener.dialogAddNewListener
import com.example.mykotlinapp.data.model.AddressProvince
import com.example.mykotlinapp.databinding.FragmentBookNextPageBinding
import com.example.mykotlinapp.features.book.adapter.PhotoBookAdapter
import com.example.mykotlinapp.features.book.adapter.TinhHuyenXaAdapter
import com.example.mykotlinapp.features.search.Adapter.SearchAdapter
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
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class BookNextPageFragment : BaseFragment(), dialogAddNewListener {

    val viewModel: BookNextPageViewModel by viewModel()
    private var mListPhotos: ArrayList<Uri>? = null

    private lateinit var binding: FragmentBookNextPageBinding
    var name: String? = ""
    var maloai = ""
    var id = ""
    var idtinh: Int = 0
    var idhuyen: Int = 0
    var idxa: Int = 0
    var thoigian: String = ""
    private var adapterTinh: TinhHuyenXaAdapter? = null
    private var photoBookAdapter: PhotoBookAdapter? = null

    private fun registerLiveData1() = with(viewModel) {
        getTinh("") //init from viewModel
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.d("data", " error ")
        }
        tinhtp.observe(viewLifecycleOwner) {

            it.remove(AddressProvince(50, "Hồ Chí Minh", "Thành phố Hồ Chí Minh"))
            it.add(0, AddressProvince(50, "Hồ Chí Minh", "Thành phố Hồ Chí Minh"))
            adapterTinh = TinhHuyenXaAdapter(requireContext(), R.layout.list_item_dropdown, it)
            adapterTinh?.itemTinhClick = {
                binding.dropdownMenu.setText(it.ten)
                registerLiveDataQuan(it.id)
                idtinh = it.id
            }
            Log.d("datatinh", " - " + it.toString())
            binding.dropdownMenu.setAdapter(adapterTinh)
        }
    }

    private fun registerLiveDataQuan(id_tinh: Int) = with(viewModel) {
        getHuyen(id_tinh) //init from viewModel
        error.observe(viewLifecycleOwner) {
            Log.d("datahuyen", " error ")
        }
        huyenquan.observe(viewLifecycleOwner) {
            adapterTinh = TinhHuyenXaAdapter(requireContext(), R.layout.list_item_dropdown, it)
            adapterTinh?.itemTinhClick = {
                binding.dropdownMenuQuan.setText(it.fullName)
                registerLiveDataPhuong(it.id)
                idhuyen = it.id
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
            adapterTinh = TinhHuyenXaAdapter(requireContext(), R.layout.list_item_dropdown, it)
            adapterTinh?.itemTinhClick = {
                binding.dropdownMenuPhuong.setText(it.fullName)
                idxa = it.id
            }
            Log.d("dataxa", " - $it")
            binding.dropdownMenuPhuong.setAdapter(adapterTinh)
        }
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
        maloai = args?.get("loai").toString()
        id = args?.get("id").toString()
        binding.tvToolbar.text = inputData.toString().toUpperCase()
        binding.tvName.text = inputData.toString().toUpperCase()

        //datepicker
        val today = Calendar.getInstance()
        binding.datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)){
                view, year, month, day ->
            val month = month + 1
            var dayselect = "$year-$month-$day"
            thoigian = dayselect
            binding.datePicker.minDate = System.currentTimeMillis()
//            Toast.makeText(requireActivity(), dayselect, Toast.LENGTH_SHORT).show()
        }

        //set adapter
        photoBookAdapter = PhotoBookAdapter(requireActivity())
        val gridLayoutManager =
            GridLayoutManager(requireActivity(), 1, LinearLayoutManager.HORIZONTAL, false)

        //set recyclerView
        binding.recyclerviewPhotoBook.layoutManager = gridLayoutManager
        binding.recyclerviewPhotoBook.isFocusable = false
        binding.recyclerviewPhotoBook.isNestedScrollingEnabled = false
        binding.recyclerviewPhotoBook.adapter = photoBookAdapter

//        ShowDialog().setOndialogAddNewListener(this)

        registerLiveData()
        registerLiveData1()
        event()

        return binding.root
    }

    private fun registerLiveData() {
        viewModel.NewLich.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Log.e("urlReal", " thanhf coong")
            if (it.accept == true) {
                ShowDialog().showDialogAddNewTrue(requireActivity())
//                requireActivity().finish()
//                startActivity(Intent(activity, MainActivity::class.java))
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

        binding.dropdownMenu.setOnClickListener {
            registerLiveData1()
            onHideSoftKeyBoard()
            binding.dropdownMenuQuan.setText("")
            binding.dropdownMenuPhuong.setText("")
        }
        binding.dropdownMenuQuan.setOnClickListener {
            onHideSoftKeyBoard()
            if (idtinh != 0) {
                registerLiveDataQuan(idtinh)
                binding.dropdownMenuPhuong.setText("")
            } else {
                Snackbar.make(
                    binding.tvName,
                    "Vui lòng chọn Tỉnh/Thành phố!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        binding.dropdownMenuPhuong.setOnClickListener {
            onHideSoftKeyBoard()
            if (idhuyen != 0) {
                registerLiveDataPhuong(idhuyen)
            } else {
                Snackbar.make(
                    binding.tvName,
                    "Vui lòng chọn Huyện/Quận",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.ivBackBookNextPage.setOnClickListener {
            onHideSoftKeyBoard()
            MainActivity.navigateFragment(MainActivity.mBook)
            MainActivity.mBookNextPageFragment = BookNextPageFragment()
        }
        binding.question.setOnClickListener {
            ShowDialog().showDialog(requireActivity())
        }
        binding.imgCameraSelect.setOnClickListener {
            onHideSoftKeyBoard()
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

            Log.e("urlReal", " ma loai thu gom " + maloai + " ---- " + id)
            var photo1: MultipartBody.Part? = null
            var photo2: MultipartBody.Part? = null
            var photo3: MultipartBody.Part? = null

            if (mListPhotos != null)
                for (i in 0..mListPhotos!!.size - 1) {
                    var uri = mListPhotos!![i]
                    var strRealPath: String? = RealPathUtil.getRealPath(requireContext(), uri)
                    Log.e("urlReal", " " + i + "---- " + strRealPath)
//            var file : File = File(strRealPath)
                    var fullSizeBitmap = BitmapFactory.decodeFile(strRealPath)
                    var reduceBitmap = ImageResizer.reduceBitmapSize(fullSizeBitmap, 210000)
                    var reducedFile = getBitmapFile(reduceBitmap)

                    var image = reducedFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                    Log.e("urlReal", "reducedFile " + i + "---- " + reducedFile.name)
                    if (i == 0) {
                        photo1 = MultipartBody.Part.createFormData(
                            "picture_1",
                            "_1-" + reducedFile.name,
                            image
                        )
                        if (mListPhotos!!.size < 2) {
                            var reducedFile2 = ""
                            var image2 =
                                reducedFile2.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                            photo2 = MultipartBody.Part.createFormData("picture_2", "", image2)
                            photo3 = MultipartBody.Part.createFormData("picture_3", "", image2)
                        }
                    }
                    if (i == 1) {
                        photo2 = MultipartBody.Part.createFormData(
                            "picture_2",
                            "_2-" + reducedFile.name,
                            image
                        )
                        if (mListPhotos!!.size < 3) {
                            var reducedFile2 = ""
                            var image2 =
                                reducedFile2.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                            photo3 = MultipartBody.Part.createFormData("picture_3", "", image2)
                        }
                    }
                    if (i == 2) {
                        photo3 = MultipartBody.Part.createFormData(
                            "picture_3",
                            "_3-" + reducedFile.name,
                            image
                        )
                    }
                }
            else {
                var reducedFile2 = ""
                var image2 = reducedFile2.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                photo1 = MultipartBody.Part.createFormData("picture_1", "", image2)
                photo2 = MultipartBody.Part.createFormData("picture_2", "", image2)
                photo3 = MultipartBody.Part.createFormData("picture_3", "", image2)
            }

//            var thoigian = binding.edtTime.text.toString()
            var diachi = binding.edtAddress.text.toString()
            var sdt = binding.edtPhoneNumber.text.toString()
            var chitiet = binding.edtContent.text.toString()
            var khoiluong = binding.edtKhoiluong.text.toString()
            var makhachhang = binding.edtCodeKHBook.text.toString()

            onHideSoftKeyBoard()
            if (thoigian == "" || diachi == "" || sdt == "" || chitiet == "") {
                ShowDialog().showDialogAddNewFalse(requireActivity())
            } else {
                progressDialog.show()
                viewModel.addNewLichThu(
                    makhachhang,
                    idtinh.toString(),
                    idhuyen.toString(),
                    idxa.toString(),
                    diachi,
                    khoiluong,
                    maloai,
                    id,
                    thoigian,
                    sdt,
                    chitiet,
                    photo1!!,
                    photo2!!,
                    photo3!!,
                )
            }
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
        val date = Calendar.getInstance()
        val minute = date.get(Calendar.MILLISECOND)

        var file = File(
            Environment.getExternalStorageDirectory()
                .toString() + File.separator + minute.toString() + "_reduced_file.png"
        )
        var bos = ByteArrayOutputStream()
        reduceBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
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

    override fun onOkClicked() {
        requireActivity().finish()
        startActivity(Intent(activity, MainActivity::class.java))
    }
}