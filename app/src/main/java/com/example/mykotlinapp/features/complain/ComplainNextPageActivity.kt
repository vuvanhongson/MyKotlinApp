package com.example.mykotlinapp.features.complain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.ActivityComplainNextPageBinding
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding

class ComplainNextPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComplainNextPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityComplainNextPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent.getStringExtra("namecomplain")
        binding.tvToolbar.text = intent.toString().toUpperCase()

        binding.ivBackComplainNextPage.setOnClickListener {
            onBackPressed()
        }
        binding.question.setOnClickListener {
            ShowDialog().showDialog(this)
        }
    }
}