package com.example.mykotlinapp.features.dumptrash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding


class DumpTrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDumpTrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityDumpTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBackDump.setOnClickListener{
            onBackPressed()
        }

    }
}