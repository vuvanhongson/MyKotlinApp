package com.example.mykotlinapp.features.support

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityWebGracBinding

class WebGracActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebGracBinding

    var urllink: String = "https://grac.vn"

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        val intent = intent
        urllink = intent.getStringExtra("urllink").toString()

        super.onCreate(savedInstanceState)
        binding = ActivityWebGracBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initControls()
        initListen()
    }

    private fun initListen() {
        binding.ivExit.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initControls() {
        binding!!.webViewGrac.settings.javaScriptEnabled = true
        binding!!.webViewGrac.webViewClient = WebViewClient()
        binding!!.webViewGrac.webChromeClient = WebChromeClient()
        binding!!.webViewGrac.loadUrl(urllink)
    }
}