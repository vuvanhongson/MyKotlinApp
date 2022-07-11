package com.example.mykotlinapp.features.complain

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.ActivityComplainNextPageBinding
import com.example.mykotlinapp.features.complain.adapter.PhotoAdapter
import com.example.mykotlinapp.util.ext.ImageResizer
import com.example.mykotlinapp.util.ext.RealPathUtil
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


class ComplainNextPageActivity : AppCompatActivity() {

    val viewModel: ComplainViewModel by viewModel()
    private var mListPhotos: ArrayList<Uri>? = null
    private lateinit var binding: ActivityComplainNextPageBinding
    private var photoAdapter: PhotoAdapter? = null
    val IMAGE_REQUEST_CODE = 100
    var id = ""
    var text = ""

        override fun onCreate(savedInstanceState: Bundle?) {

        window.statusBarColor = ContextCompat.getColor(this, R.color.grac_green)

        super.onCreate(savedInstanceState)
        binding = ActivityComplainNextPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set name title toolbar
        val intent = intent
        val bundle = intent.extras
//        val text = intent.getStringExtra("namecomplain")
        if(bundle != null){
            text = bundle.getString("namecomplain").toString()
            id= bundle.getString("id").toString();
        }
        binding.tvToolbar.text = text.toString().toUpperCase()

        //set adapter
        photoAdapter = PhotoAdapter(this)
        val gridLayoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false)

        //set recyclerView
        binding.recyclerviewPhoto.layoutManager = gridLayoutManager
        binding.recyclerviewPhoto.isFocusable = false
        binding.recyclerviewPhoto.isNestedScrollingEnabled = false
        binding.recyclerviewPhoto.adapter = photoAdapter

            registerLiveData()
            event()

    }

    private fun registerLiveData() {
        viewModel.NewComplain.observe(this) {
            Log.e("urlReal", " thanhf coong")
            if (it.accept == true) {
                ShowDialog().showDialogAddNewTrue(this)
                this.finish()
                startActivity(Intent(this, MainActivity::class.java))
            } else
                ShowDialog().showDialogAddNewFalse(this)
        }
        viewModel.error.observe(this) {
            Log.e("urlReal", " thaast baij")
            ShowDialog().showDialogAddNewError(this)
        }
    }

    private fun event() {
        binding.ivBackComplainNextPage.setOnClickListener {
            onBackPressed()
        }
        binding.question.setOnClickListener {
            ShowDialog().showDialog(this)
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

        binding.btnAddNewComplain.setOnClickListener {
            Log.e("urlReal", " ma loai thu gom " + id + " ---- " + id)
            var photo1: MultipartBody.Part? = null
            var photo2: MultipartBody.Part? = null
            var photo3: MultipartBody.Part? = null

            if (mListPhotos != null)
                for (i in 0..mListPhotos!!.size - 1) {
                    var uri = mListPhotos!![i]
                    var strRealPath: String? = RealPathUtil.getRealPath(this, uri)
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

            var makhach = binding.edtCodeKH.text.toString()
            var noidung = binding.edtContentComplain.text.toString()
            var sdt = binding.edtPhoneNumber.text.toString()

            if (makhach == "" || noidung == "" || sdt == "") {
                ShowDialog().showDialogAddNewFalse(this)
            } else {
                viewModel.addNewKhieuNaiPhanAnh(
                    makhach,
                    sdt,
                    "",
                    noidung,
                    id,
                    photo1!!,
                    photo2!!,
                    photo3!!,
                )
            }
        }

    }

    //check permission
    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
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
                    this@ComplainNextPageActivity,
                    "Permission Denied\n$deniedPermissions", Toast.LENGTH_SHORT
                ).show()
            }
        }
        TedPermission.with(this@ComplainNextPageActivity)
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
        val listener = TedBottomPicker.OnMultiImageSelectedListener {
                uriList -> photoAdapter!!.setData(uriList)
                mListPhotos = uriList
            }
        val tedBottomPicker = TedBottomPicker.Builder(this@ComplainNextPageActivity)
            .setOnMultiImageSelectedListener(listener)
            .setCompleteButtonText("XONG")
            .setEmptySelectionText("Bạn chưa chọn ảnh!")
            .setTitle("Chọn tối đa 3 ảnh!")
            .setSelectMinCount(1)
            .setSelectMinCountErrorText("Bạn phải chọn tối thiểu 1 ảnh!")
            .setSelectMaxCount(3)
            .setSelectMaxCountErrorText("Bạn chỉ có thể chọn tối đa 3 ảnh!")
            .create()
        tedBottomPicker.show(supportFragmentManager)
    }

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

}