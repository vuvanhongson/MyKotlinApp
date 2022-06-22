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

        initControl()

    }

    private fun initControl()
    {
        binding.ivBackGame.setOnClickListener {
            onBackPressed()
        }

    }

    override fun Game1Clicked() {
        val intent = Intent(this, GameWebViewActivity::class.java)
        intent.putExtra("url", "https://grac.vn/game")
        startActivity(intent)
    }

    override fun Game2Clicked() {
        val intent = Intent(this, GameWebViewActivity::class.java)
        intent.putExtra("url", "https://grac.vn/game2")
        startActivity(intent)
    }

    override fun Game3Clicked() {
        val intent = Intent(this, GameWebViewVerticalActivity::class.java)
        intent.putExtra("url", "https://grac.vn/game3")
        startActivity(intent)
    }

    override fun Game4Clicked() {
        val intent = Intent(this, GameWebViewVerticalActivity::class.java)
        intent.putExtra("url", "https://grac.vn/20-cau-hoi-trac-nghiem-ve-quan-ly-va-phan-loai-chat-thai/")
        startActivity(intent)
    }

}