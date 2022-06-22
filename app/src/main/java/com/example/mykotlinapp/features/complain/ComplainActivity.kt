package com.example.mykotlinapp.features.complain

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityComplainBinding
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding
import com.example.mykotlinapp.features.search.InformationFragment


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

        binding.btnKetiep.setOnClickListener {
            val i = Intent(this, ComplainNextPageActivity::class.java)
            startActivity(i)
        }

    }
}








