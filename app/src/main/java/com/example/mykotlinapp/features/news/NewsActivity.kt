package com.example.mykotlinapp.features.news

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.ActivityNewsBinding
import com.example.mykotlinapp.features.garbagePrice.GarbagePriceAdapter
import com.example.mykotlinapp.features.garbagePrice.PriceWebViewActivity
import kotlinx.android.synthetic.main.fragment_home.*

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

    private var adapter: NewsAdapter? = null

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //recyclerView
        initNews()
        getNewsData()





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
        adapter = NewsAdapter(newsArrayList)
        binding.recyclerviewNews.adapter = adapter

        adapter?.itemTinhClick = {
            if(it == 0)
            {
                val intent = Intent(this, PriceWebViewActivity::class.java)
                intent.putExtra(
                    "urlprice",
                    "https://grac.vn/#"
                )
                startActivity(intent)
            }
            if(it == 1)
            {
                val intent = Intent(this, PriceWebViewActivity::class.java)
                intent.putExtra(
                    "urlprice",
                    "https://grac.vn/#"
                )
                startActivity(intent)
            }
            if(it == 2)
            {
                val intent = Intent(this, PriceWebViewActivity::class.java)
                intent.putExtra(
                    "urlprice",
                    "https://grac.vn/#"
                )
                startActivity(intent)
            }
        }
    }

    private fun initNews() {
        RecyclerView_news = binding.recyclerviewNews
        RecyclerView_news.layoutManager = GridLayoutManager(this, 2)
        RecyclerView_news.isNestedScrollingEnabled = false
        RecyclerView_news.setHasFixedSize(true)
        newsArrayList = arrayListOf<News>()

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
            "GI?? TI???N R??C, TIN T???C",
            "GI?? TI???N R??C, TIN T???C",
            "GI?? TI???N R??C, TIN T???C",
        )
        tvTitleCenter2 = arrayOf(
            "C??n c??? ph??p l?? ti???n r??c\nTP.HCM",
            "C??n c??? ph??p l?? ti???n r??c\nTP.HCM",
            "C??n c??? ph??p l?? ti???n r??c\nTP.HCM",
        )
        tvContent = arrayOf(
            "Gi?? d???ch v??? thu gom, v???n chuy???n, x??? l?? ch???t th???i r???n sinh ho???t (vi???t t???t l?? r??c) ?????i v???i h??? gia ????nh",
            "Gi?? d???ch v??? thu gom, v???n chuy???n, x??? l?? ch???t th???i r???n sinh ho???t (vi???t t???t l?? r??c) ?????i v???i h??? gia ????nh",
            "Gi?? d???ch v??? thu gom, v???n chuy???n, x??? l?? ch???t th???i r???n sinh ho???t (vi???t t???t l?? r??c) ?????i v???i h??? gia ????nh",
        )
    }
}