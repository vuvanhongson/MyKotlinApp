package com.example.mykotlinapp.features.question

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityGameWebviewVerticalBinding
import com.example.mykotlinapp.databinding.ActivityQuestionWebViewBinding

class QuestionWebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionWebViewBinding

    var urlQuestion : String = "https://grac.vn/cau-hoi-thuong-gap/"

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        val intent = intent
        urlQuestion = intent.getStringExtra("urlquestion").toString()

        super.onCreate(savedInstanceState)
        binding = ActivityQuestionWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initControls()
        initListen()
    }

    private fun initListen() {
        binding.ivExit.setOnClickListener{
            onBackPressed()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initControls() {
        binding!!.webView.settings.javaScriptEnabled = true
        binding!!.webView.webViewClient = WebViewClient()
        binding!!.webView.webChromeClient = WebChromeClient()
        binding!!.webView.loadUrl(urlQuestion)
    }
}