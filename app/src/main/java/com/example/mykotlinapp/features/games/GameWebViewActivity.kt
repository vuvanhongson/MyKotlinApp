package com.example.mykotlinapp.features.games

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinapp.databinding.ActivityGameWebviewBinding

class GameWebViewActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityGameWebviewBinding

    var urlGame : String = "https://grac.vn/game"


    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        getActivity()?.setRequestedOrientation(
//            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        val intent = intent
        urlGame = intent.getStringExtra("url").toString()

        super.onCreate(savedInstanceState)
        binding = ActivityGameWebviewBinding.inflate(layoutInflater)
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