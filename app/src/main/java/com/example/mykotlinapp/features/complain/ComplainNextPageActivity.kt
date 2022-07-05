package com.example.mykotlinapp.features.complain

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.ActivityComplainNextPageBinding
import com.example.mykotlinapp.features.complain.adapter.PhotoAdapter
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import gun0912.tedbottompicker.TedBottomPicker

class ComplainNextPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComplainNextPageBinding
    private var photoAdapter: PhotoAdapter? = null
    val IMAGE_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {

        window.statusBarColor = ContextCompat.getColor(this, R.color.grac_green)

        super.onCreate(savedInstanceState)
        binding = ActivityComplainNextPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set name title toolbar
        val intent = intent.getStringExtra("namecomplain")
        binding.tvToolbar.text = intent.toString().toUpperCase()

        //set adapter
        photoAdapter = PhotoAdapter(this)
        val gridLayoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false)

        //set recyclerView
        binding.recyclerviewPhoto.layoutManager = gridLayoutManager
        binding.recyclerviewPhoto.isFocusable = false
        binding.recyclerviewPhoto.isNestedScrollingEnabled = false
        binding.recyclerviewPhoto.adapter = photoAdapter

        event()

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
        val listener =
            TedBottomPicker.OnMultiImageSelectedListener { uriList -> photoAdapter!!.setData(uriList) }
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