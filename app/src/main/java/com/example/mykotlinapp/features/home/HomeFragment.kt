package com.example.mykotlinapp.features.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.os.bundleOf
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
import com.example.mykotlinapp.features.home.schedule.AdapterGridCollectionSchedule
import com.example.mykotlinapp.features.news.NewsActivity
import com.example.mykotlinapp.features.home.schedule.AdapterListCollectionSchedule
import com.example.mykotlinapp.features.slide.ViewPageAdapter
import com.example.mykotlinapp.util.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), ItemButonRecyclerviewListener {
    val viewModel: HomeViewModel by viewModel()

    private lateinit var newRecyclerView: RecyclerView

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPageAdapter: ViewPageAdapter

    //adapter
    private var adapterGird: AdapterGridCollectionSchedule? = null
    private var adapterList: AdapterListCollectionSchedule? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPageAdapter = ViewPageAdapter(
            parentFragmentManager!!,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )



    }

    private fun registerLiveData() = with(viewModel) {
        viewModel.getLich("ASC", 1 , 10)
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Snackbar.make(binding.tvRcview, it.toString(), Snackbar.LENGTH_SHORT).show()
        }
        lichgonracDESC.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            adapterList?.setData(it)
            adapterGird?.setData(it)
            Log.d("data", " - " + it.toString() )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
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
//            Log.d("scroll", "x top = $scrollX y = $scrollY")

            if (binding.scrollView.scrollY < 600) {
                binding.view2.alpha = 1.toFloat() - scrollY
                binding.view2.translationY = -scrollY * 300
                binding.view3.alpha = scrollY
            }
        })

        adapterList = AdapterListCollectionSchedule()
        adapterGird = AdapterGridCollectionSchedule()
        //recyclerView

        newRecyclerView = binding.recyclerview
        newRecyclerView.layoutManager = GridLayoutManager(context, 1)
        newRecyclerView.isNestedScrollingEnabled = true
        newRecyclerView.adapter = adapterList


        newRecyclerView.setHasFixedSize(true)

        binding.handler = this

        initCtrl()
        initRefresh()

        return binding.root
    }




    private fun initRefresh() {
        //swipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
                progressDialog.show()
                viewModel.getLich("DESC", 1 , 10)
                viewModel.lichgonracDESC.observe(viewLifecycleOwner) {
                    progressDialog.dismiss()
                    adapterList?.setData(it)
                    adapterGird?.setData(it)
                    Log.d("data", " - " + it.toString() )
                }
            }, 1000)
        }
        //swipeRefreshLayout custom color
        binding.swipeRefreshLayout.setColorSchemeResources(
            R.color.grac_green,
            R.color.grac_orange,
            R.color.purple_200
        )
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



    override fun onListClicked() {
        changeWhiteButon()
        binding.ivList.setImageResource(R.drawable.ic_list_green)
        newRecyclerView.layoutManager = GridLayoutManager(context, 1)
        newRecyclerView.adapter = adapterList
    }

    override fun onMapClicked() {
        changeWhiteButon()
        binding.ivMap.setImageResource(R.drawable.ic_map_green)
    }

    override fun onSortClicked() {
        changeWhiteButon()
        binding.ivSort.setImageResource(R.drawable.ic_sort_green)
    }

    override fun onGridClicked() {
        changeWhiteButon()
        binding.ivGrid.setImageResource(R.drawable.ic_them_green)
        newRecyclerView.layoutManager = GridLayoutManager(context, 2)
        newRecyclerView.adapter = adapterGird
    }

    fun changeWhiteButon() {
        binding.ivList.setImageResource(R.drawable.ic_list_gray)

        binding.ivMap.setImageResource(R.drawable.ic_map_gray)

        binding.ivSort.setImageResource(R.drawable.ic_sort_gray)

        binding.ivGrid.setImageResource(R.drawable.ic_them_gray)
    }


    companion object {
        fun newInstance() = HomeFragment().apply {
            arguments = bundleOf()
        }
    }
}