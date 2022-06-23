package com.example.mykotlinapp.features.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentHomeBinding
import com.example.mykotlinapp.features.complain.ComplainActivity
import com.example.mykotlinapp.features.dumptrash.DumpTrashActivity
import com.example.mykotlinapp.features.games.GamesActivity
import com.example.mykotlinapp.features.garbagePrice.GarbagePriceActivity
import com.example.mykotlinapp.features.news.NewsActivity
import com.example.mykotlinapp.features.schedule.AdapterCollectionSchedule
import com.example.mykotlinapp.features.schedule.Schedule
import com.example.mykotlinapp.features.slide.ViewPageAdapter
import com.example.mykotlinapp.util.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), ItemButonRecyclerviewListener {

    //recyclerview
    val viewModel: HomeViewModel by viewModel()

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Schedule>
    lateinit var imageWork: Array<Int>
    lateinit var nameWork: Array<String>
    lateinit var addressWork: Array<String>
    lateinit var dateWork: Array<String>


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPageAdapter: ViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPageAdapter = ViewPageAdapter(
            parentFragmentManager!!,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )



    }

    private fun registerLiveData() = with(viewModel) {
//        movies.observe(viewLifecycleOwner) {
//            progressDialog.dismiss()
//            Log.d("data", " - " + it.toString() )
//        }
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Snackbar.make(binding.tvRcview, it.toString(), Snackbar.LENGTH_SHORT).show()
        }
        lichgonrac.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
//            hFilmAdapter?.setData(it)
            Log.d("data", " - " + it.toString() )
        }
//        viewModel.error.observe(viewLifecycleOwner) {
//            progressDialog.dismiss()
////            Snackbar.make(binding.appToolbar, it.toString(), Snackbar.LENGTH_SHORT).show()
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater)


        viewPageAdapter = ViewPageAdapter(
            parentFragmentManager!!,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        progressDialog.show()
        registerLiveData()

        binding.viewPager.setAdapter(viewPageAdapter)
        binding.circleIndicartor.setViewPager(binding.viewPager)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 2) {

                } else {
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        var x = binding.scrollView.scrollX
        Log.d("scroll", " x - " + x)


        binding!!.scrollView.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            val scrollY = binding!!.scrollView.scrollY * 2 / 1000.toFloat() // For ScrollView
            val scrollX = binding!!.scrollView.scrollX / 1000.toFloat()// For HorizontalScrollView
            // DO SOMETHING WITH THE SCROLL COORDINATES
            Log.d("scroll", "x top = $scrollX y = $scrollY")

            if (binding.scrollView.scrollY < 600) {
//            binding.view2.animate().alphaBy(1.0.toFloat())
//                .alpha(1.toFloat() - scrollY).duration = 100
                binding.view2.alpha = 1.toFloat() - scrollY
                binding.view2.translationY = -scrollY * 300

//            binding.view3.animate().alphaBy(0.0.toFloat())
//                .alpha(scrollY).duration = 100
                binding.view3.alpha = scrollY
            }
        })

        //recyclerView
        initWork()

        newRecyclerView = binding.recyclerview
        newRecyclerView.layoutManager = GridLayoutManager(context, 1)
        newRecyclerView.isNestedScrollingEnabled = false
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<Schedule>()
        getUserData()


        binding.handler = this

        initCtrl()
        initRefresh()

        return binding.root
    }




    private fun initRefresh() {
        //swipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
//            Log.d("aaa","bbb")
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
                progressDialog.show()
                registerLiveData()
            }, 3000)
        }
        //swipeRefreshLayout custom color
        binding.swipeRefreshLayout.setColorSchemeResources(
            R.color.grac_green,
            R.color.grac_orange,
            R.color.purple_200
        );
    }

    private fun initCtrl() {
        binding.root.bt_price.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                startActivity(Intent(activity, GarbagePriceActivity::class.java))
            }
        })

        binding.root.bt_trash.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                startActivity(Intent(activity, DumpTrashActivity::class.java))
            }
        })

        binding.root.bt_complain.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                startActivity(Intent(activity, ComplainActivity::class.java))
            }
        })

        binding.root.bt_game.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                startActivity(Intent(activity, GamesActivity::class.java))
            }
        })

        binding.root.bt_news.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                startActivity(Intent(activity, NewsActivity::class.java))
            }
        })
    }

    private fun initWork() {
        imageWork = arrayOf(
            R.drawable.item_ban,
            R.drawable.item_ban2,
            R.drawable.item_ban3,
            R.drawable.item_ban4,
            R.drawable.item_ban,
            R.drawable.item_ban2,
            R.drawable.item_ban3,
            R.drawable.item_ban4,
            R.drawable.item_ban,
            R.drawable.item_ban2,
            R.drawable.item_ban3,
            R.drawable.item_ban4,
            R.drawable.item_ban,
            R.drawable.item_ban2,
            R.drawable.item_ban3,
            R.drawable.item_ban4,
        )
        nameWork = arrayOf(
            "recyclerView 1",
            "recyclerView 2",
            "recyclerView 3",
            "recyclerView 4",
            "recyclerView 5",
            "recyclerView 6",
            "recyclerView 7",
            "recyclerView 8",
            "recyclerView 1",
            "recyclerView 2",
            "recyclerView 3",
            "recyclerView 4",
            "recyclerView 5",
            "recyclerView 6",
            "recyclerView 7",
            "recyclerView 8",
        )
        addressWork = arrayOf(
            "address 1",
            "address 2",
            "address 3",
            "address 4",
            "address 5",
            "address 6",
            "address 7",
            "address 8",
            "address 1",
            "address 2",
            "address 3",
            "address 4",
            "address 5",
            "address 6",
            "address 7",
            "address 8",
        )
        dateWork = arrayOf(
            "11/11/2021",
            "12/12/2021",
            "10/10/2021",
            "10/6/2022",
            "11/11/2021",
            "12/12/2021",
            "10/10/2021",
            "10/6/2022",
            "11/11/2021",
            "12/12/2021",
            "10/10/2021",
            "10/6/2022",
            "11/11/2021",
            "12/12/2021",
            "10/10/2021",
            "10/6/2022",
        )

    }

    private fun getUserData() {
        for (i in imageWork.indices) {
            val work = Schedule(imageWork[i], nameWork[i], addressWork[i], dateWork[i])
            newArrayList.add(work)
        }
        newRecyclerView.adapter = AdapterCollectionSchedule(newArrayList)
    }

    override fun onListClicked() {
        Log.d("buton", "Clicked")
        changeWhiteButon()
        binding.ivList.setImageResource(R.drawable.ic_list_green)
//        binding.llList.setBackgroundResource(R.drawable.bg_green_radius_bottom_s)
    }

    override fun onMapClicked() {
        Log.d("buton", "Clicked")
        changeWhiteButon()
        binding.ivMap.setImageResource(R.drawable.ic_map_green)
//        binding.llMap.setBackgroundResource(R.drawable.bg_green_radius_bottom_s)
    }

    override fun onSortClicked() {
        Log.d("buton", "Clicked")
        changeWhiteButon()
        binding.ivSort.setImageResource(R.drawable.ic_sort_green)
//        binding.llSort.setBackgroundResource(R.drawable.bg_green_radius_bottom_s)
    }

    override fun onGridClicked() {
        changeWhiteButon()
        binding.ivGrid.setImageResource(R.drawable.ic_them_green)
//        binding.llGrid.setBackgroundResource(R.drawable.bg_green_radius_bottom_s)
    }

    fun changeWhiteButon() {
        binding.ivList.setImageResource(R.drawable.ic_list_gray)
//        binding.llList.setBackgroundResource(R.drawable.bg_radius_white)

        binding.ivMap.setImageResource(R.drawable.ic_map_gray)
//        binding.llMap.setBackgroundResource(R.drawable.bg_radius_white)

        binding.ivSort.setImageResource(R.drawable.ic_sort_gray)
//        binding.llSort.setBackgroundResource(R.drawable.bg_radius_white)

        binding.ivGrid.setImageResource(R.drawable.ic_them_gray)
//        binding.llGrid.setBackgroundResource(R.drawable.bg_radius_white)
    }

}