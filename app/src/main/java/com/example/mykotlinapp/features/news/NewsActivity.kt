package com.example.mykotlinapp.features.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    //recyclerView
    private lateinit var RecyclerView_news: RecyclerView
    private lateinit var newsArrayList: ArrayList<News>
    //    lateinit var imageTitleHeader: Array<Int>
    lateinit var imageBgHeader: Array<Int>
    lateinit var tvTitleHeader: Array<String>
    lateinit var tvTitleCenter: Array<String>
    lateinit var tvTitleCenter2: Array<String>
    lateinit var tvContent: Array<String>

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)

        //recyclerView
        initNews()
        getNewsData()


        setContentView(binding.root)


        binding.ivBackNews.setOnClickListener{
            onBackPressed()
        }
        binding.question.setOnClickListener {
            ShowDialog().showDialog(this)
        }
    }

    private fun getNewsData() {
        for (i in imageBgHeader.indices) {
            val news = News(imageBgHeader[i], tvTitleHeader[i], tvTitleCenter[i], tvTitleCenter2[i], tvContent[i])
            newsArrayList.add(news)
        }
        RecyclerView_news.adapter = NewsAdapter(newsArrayList)
    }

    private fun initNews() {
        RecyclerView_news = binding.recyclerviewNews
        RecyclerView_news.layoutManager = GridLayoutManager(this, 2)
        RecyclerView_news.isNestedScrollingEnabled = false
        RecyclerView_news.setHasFixedSize(true)
        newsArrayList = arrayListOf<News>()

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