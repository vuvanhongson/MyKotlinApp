package com.example.mykotlinapp.features.garbagePrice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding
import com.example.mykotlinapp.databinding.ActivityGarbagePriceBinding
import com.example.mykotlinapp.features.news.News
import com.example.mykotlinapp.features.news.NewsAdapter
import com.example.mykotlinapp.features.question.QuestionWebViewActivity
import com.example.mykotlinapp.features.search.Adapter.SearchAdapter

class GarbagePriceActivity : AppCompatActivity() {

    //recyclerView
    private lateinit var recyclerviewPrice: RecyclerView
    private lateinit var newsArrayList: ArrayList<GarbagePrice>

    //    lateinit var imageTitleHeader: Array<Int>
    lateinit var imageBgHeader: Array<Int>
    lateinit var tvTitleHeader: Array<String>
    lateinit var tvTitleCenter: Array<String>
    lateinit var tvTitleCenter2: Array<String>
    lateinit var tvContent: Array<String>

    private var adapter: GarbagePriceAdapter? = null

    var urlprice: String = "https://grac.vn/category/gia-tien-rac/"

    private lateinit var binding: ActivityGarbagePriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityGarbagePriceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //recyclerView
        initNews()
        getNewsData()
//        initControls()

        binding.ivBackPrice.setOnClickListener {
            onBackPressed()
        }
        binding.question.setOnClickListener {
            ShowDialog().showDialog(this)
        }

        binding.recyclerviewPrice.setOnClickListener {
//            val intent = Intent(this, PriceWebViewActivity::class.java)
//            intent.putExtra(
//                "urlprice",
//                "https://grac.vn/category/gia-tien-rac/"
//            )
//            startActivity(intent)
        }


    }

    //    @SuppressLint("SetJavaScriptEnabled")
//    private fun initControls() {
//        binding!!.webViewGiatienrac.settings.javaScriptEnabled = true
//        binding!!.webViewGiatienrac.webViewClient = WebViewClient()
//        binding!!.webViewGiatienrac.webChromeClient = WebChromeClient()
//        binding!!.webViewGiatienrac.loadUrl(urlprice)
//    }
    private fun getNewsData() {
        for (i in imageBgHeader.indices) {
            val news = GarbagePrice(
                imageBgHeader[i],
                tvTitleHeader[i],
                tvTitleCenter[i],
                tvTitleCenter2[i],
                tvContent[i]
            )
            newsArrayList.add(news)
        }
        adapter = GarbagePriceAdapter(newsArrayList)
        recyclerviewPrice.adapter = adapter

        adapter?.itemTinhClick = {
            if(it == 0)
            {
                val intent = Intent(this, PriceWebViewActivity::class.java)
                intent.putExtra(
                    "urlprice",
                    "https://grac.vn/category/gia-tien-rac/"
                )
                startActivity(intent)
            }
            if(it == 1)
            {
                val intent = Intent(this, PriceWebViewActivity::class.java)
                intent.putExtra(
                    "urlprice",
                    "https://grac.vn/category/gia-tien-rac/"
                )
                startActivity(intent)
            }
            if(it == 2)
            {
                val intent = Intent(this, PriceWebViewActivity::class.java)
                intent.putExtra(
                    "urlprice",
                    "https://grac.vn/category/gia-tien-rac/"
                )
                startActivity(intent)
            }
        }

    }

    private fun initNews() {
        recyclerviewPrice = binding.recyclerviewPrice
        recyclerviewPrice.layoutManager = GridLayoutManager(this, 2)
        recyclerviewPrice.isNestedScrollingEnabled = false
        recyclerviewPrice.setHasFixedSize(true)
        newsArrayList = arrayListOf<GarbagePrice>()

        imageBgHeader = arrayOf(
            R.drawable.anhminhhoa,
            R.drawable.anhminhhoa2,
            R.drawable.anhminhhoa3,
        )

//        imageTitleHeader = arrayOf(
//            R.drawable.ic_datlich_gray,
//            R.drawable.ic_datlich_gray,
//            R.drawable.ic_datlich_gray,
//        )
        tvTitleHeader = arrayOf(
            "Grac.vn",
            "Grac.vn",
            "Grac.vn",
        )
        tvTitleCenter = arrayOf(
            "GIÁ TIỀN RÁC, TIN TỨC",
            "GIÁ TIỀN RÁC, TIN TỨC",
            "GIÁ TIỀN RÁC, TIN TỨC",
        )
        tvTitleCenter2 = arrayOf(
            "Căn cứ pháp lý tiền rác\nTP.HCM",
            "Căn cứ pháp lý tiền rác\nTP.HCM",
            "Căn cứ pháp lý tiền rác\nTP.HCM",
        )
        tvContent = arrayOf(
            "Giá dịch vụ thu gom, vận chuyển, xử lý chất thải rắn sinh hoạt (viết tắt là rác) đối với hộ gia đình",
            "Giá dịch vụ thu gom, vận chuyển, xử lý chất thải rắn sinh hoạt (viết tắt là rác) đối với hộ gia đình",
            "Giá dịch vụ thu gom, vận chuyển, xử lý chất thải rắn sinh hoạt (viết tắt là rác) đối với hộ gia đình",
        )
    }
}