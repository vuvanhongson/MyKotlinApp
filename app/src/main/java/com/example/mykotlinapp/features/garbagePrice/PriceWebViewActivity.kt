package com.example.mykotlinapp.features.garbagePrice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityGameWebviewVerticalBinding
import com.example.mykotlinapp.databinding.ActivityPriceWebViewBinding
import com.example.mykotlinapp.databinding.ActivityQuestionWebViewBinding

class PriceWebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPriceWebViewBinding

    var urlprice : String = "https://grac.vn/category/gia-tien-rac/"

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        val intent = intent
        urlprice = intent.getStringExtra("urlprice").toString()

        super.onCreate(savedInstanceState)
        binding = ActivityPriceWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initControls()
        initListen()
    }

    private fun initListen() {
        binding.backpage.setOnClickListener{
            onBackPressed()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initControls() {
        binding!!.webView.settings.javaScriptEnabled = true
        binding!!.webView.webViewClient = WebViewClient()
        binding!!.webView.webChromeClient = WebChromeClient()
        binding!!.webView.loadUrl(urlprice)
    }
}