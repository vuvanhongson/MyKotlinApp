package com.example.mykotlinapp.features.games

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.BottomBar
import com.example.mykotlinapp.databinding.ActivityGamesBinding
import com.example.mykotlinapp.features.garbagePrice.GarbagePriceActivity

class GamesActivity : AppCompatActivity() , GameItemHandler {

    private lateinit var binding: ActivityGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.handler = this

    }

    override fun Game1Clicked() {
        startActivity(Intent(applicationContext, GameWebViewActivity("https://grac.vn/game2")::class.java))
    }

    override fun Game2Clicked() {

    }

    override fun Game3Clicked() {

    }

    override fun Game4Clicked() {

    }

}