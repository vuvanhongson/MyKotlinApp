package com.example.mykotlinapp.features.games

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityGameWebviewVerticalBinding

class GameWebViewVerticalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameWebviewVerticalBinding

    var urlGame : String = "https://grac.vn/game3"


    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

//        getWindow().setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

//        getActivity()?.setRequestedOrientation(
//            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        val intent = intent
        urlGame = intent.getStringExtra("url").toString()

        super.onCreate(savedInstanceState)
        binding = ActivityGameWebviewVerticalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initControls()
        initListen()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initControls() {
        binding!!.webView.settings.javaScriptEnabled = true
        binding!!.webView.webViewClient = WebViewClient()
        binding!!.webView.webChromeClient = WebChromeClient()
        binding!!.webView.loadUrl(urlGame)
//        binding!!.informationToolbarLayout.textToolbar = getString(R.string.tutorial)
//        binding!!.informationToolbarLayout.setOnBackClickedListener { v: View? -> navController.popBackStack() }
    }

    private fun initListen()
    {
        binding.ivExit.setOnClickListener{
            onBackPressed()
        }
    }

}