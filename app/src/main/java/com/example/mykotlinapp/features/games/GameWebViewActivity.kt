package com.example.mykotlinapp.features.games

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinapp.databinding.ActivityGameWebviewBinding

class GameWebViewActivity (url : String) : AppCompatActivity() {

    private lateinit var binding: ActivityGameWebviewBinding

    var urlGame : String = ""

    init {
        this.urlGame = url
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        getActivity()?.setRequestedOrientation(
//            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState)
        binding = ActivityGameWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initControls()
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

}