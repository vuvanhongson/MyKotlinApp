package com.example.mykotlinapp.features.dumptrash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding
import com.example.mykotlinapp.features.support.WebGracActivity

class DumpTrashActivity : AppCompatActivity(), DumpTrashOnClickListenner {
    private lateinit var binding: ActivityDumpTrashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))
        super.onCreate(savedInstanceState)
        binding = ActivityDumpTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dumptrash = this
    }

    override fun trashItemClick1() {
        val intent = Intent(this, WebGracActivity::class.java)
        intent.putExtra(
            "urllink",
            "https://grac.vn/danh-muc/phan-loai-rac-tai-nguon/"
        )
        startActivity(intent)
    }

    override fun trashItemClick2() {
        val intent = Intent(this, WebGracActivity::class.java)
        intent.putExtra(
            "urllink",
            "https://grac.vn/danh-muc/rac-sinh-hoat-thong-thuong/"
        )
        startActivity(intent)
    }

    override fun trashItemClick3() {
        val intent = Intent(this, WebGracActivity::class.java)
        intent.putExtra(
            "urllink",
            "https://grac.vn/danh-muc/rac-thu-gom-dac-biet/"
        )
        startActivity(intent)
    }

    override fun trashItemClick4() {
        val intent = Intent(this, WebGracActivity::class.java)
        intent.putExtra(
            "urllink",
            "https://grac.vn/danh-muc/rac-tai-che/"
        )
        startActivity(intent)
    }

    override fun trashItemClick5() {
        val intent = Intent(this, WebGracActivity::class.java)
        intent.putExtra(
            "urllink",
            "https://grac.vn/danh-muc/diem-thu-hoi/"
        )
        startActivity(intent)
    }

    override fun back() {
        onBackPressed()
    }

    override fun question() {
        ShowDialog().showDialog(this)
    }
}