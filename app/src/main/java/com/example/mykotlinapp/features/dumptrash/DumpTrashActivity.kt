package com.example.mykotlinapp.features.dumptrash

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding


class DumpTrashActivity : AppCompatActivity() , DumpTrashOnClickListenner{

    private lateinit var binding: ActivityDumpTrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityDumpTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dumptrash = this

        binding.ivBackDump.setOnClickListener{
            onBackPressed()
        }

    }

    override fun trashItemClick1() {
        Toast.makeText(applicationContext, "link grac.vn", Toast.LENGTH_SHORT).show()
    }

    override fun trashItemClick2() {
        Toast.makeText(applicationContext, "link grac.vn", Toast.LENGTH_SHORT).show()
    }

    override fun trashItemClick3() {
        Toast.makeText(applicationContext, "link grac.vn", Toast.LENGTH_SHORT).show()
    }

    override fun trashItemClick4() {
        Toast.makeText(applicationContext, "link grac.vn", Toast.LENGTH_SHORT).show()
    }

    override fun trashItemClick5() {
        Toast.makeText(applicationContext, "link grac.vn", Toast.LENGTH_SHORT).show()
    }
}