package com.example.mykotlinapp.features.games

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding
import com.example.mykotlinapp.databinding.ActivityGamesBinding

class GamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBackGame.setOnClickListener {
            onBackPressed()
        }
    }
}