package com.example.mykotlinapp.features.complain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityComplainBinding
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding


class ComplainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComplainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityComplainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBackComplain.setOnClickListener {
            onBackPressed()
        }
    }
}








