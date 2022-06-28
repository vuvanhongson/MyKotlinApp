package com.example.mykotlinapp.features.garbagePrice

import android.os.Bundle
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

    private lateinit var binding: ActivityGarbagePriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityGarbagePriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recyclerView
        initNews()
        getNewsData()

        binding.ivBackPrice.setOnClickListener {
            onBackPressed()
        }
        binding.question.setOnClickListener {
            ShowDialog().showDialog(this)
        }
    }

    private fun getNewsData() {
        for (i in imageBgHeader.indices) {
            val news = GarbagePrice(imageBgHeader[i], tvTitleHeader[i], tvTitleCenter[i], tvTitleCenter2[i], tvContent[i])
            newsArrayList.add(news)
        }
        recyclerviewPrice.adapter = GarbagePriceAdapter(newsArrayList)
    }

    private fun initNews() {
        recyclerviewPrice = binding.recyclerviewPrice
        recyclerviewPrice.layoutManager = GridLayoutManager(this, 2)
        recyclerviewPrice.isNestedScrollingEnabled = false
        recyclerviewPrice.setHasFixedSize(true)
        newsArrayList = arrayListOf<GarbagePrice>()

        imageBgHeader = arrayOf(
            R.drawable.bg_item_news,
            R.drawable.bg_item_news,
            R.drawable.bg_item_news,
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