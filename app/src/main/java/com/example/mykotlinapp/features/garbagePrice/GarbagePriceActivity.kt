package com.example.mykotlinapp.features.garbagePrice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding
import com.example.mykotlinapp.databinding.ActivityGarbagePriceBinding

class GarbagePriceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGarbagePriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityGarbagePriceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBackPrice.setOnClickListener {
            onBackPressed()
        }
    }
}