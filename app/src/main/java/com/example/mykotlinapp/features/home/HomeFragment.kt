package com.example.mykotlinapp.features.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.AbsListView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.common.listener.SearchOnClickListener
import com.example.mykotlinapp.databinding.FragmentHomeBinding
import com.example.mykotlinapp.features.complain.ComplainActivity
import com.example.mykotlinapp.features.dumptrash.DumpTrashActivity
import com.example.mykotlinapp.features.games.GamesActivity
import com.example.mykotlinapp.features.garbagePrice.GarbagePriceActivity
import com.example.mykotlinapp.features.home.schedule.AdapterGridCollectionSchedule
import com.example.mykotlinapp.features.home.schedule.AdapterListCollectionSchedule
import com.example.mykotlinapp.features.map.MapsActivity
import com.example.mykotlinapp.features.news.NewsActivity
import com.example.mykotlinapp.features.question.QuestionWebViewActivity
import com.example.mykotlinapp.features.search.InformationFragment
import com.example.mykotlinapp.features.search.InformationViewModel
import com.example.mykotlinapp.features.slide.ViewPageAdapter
import com.example.mykotlinapp.util.base.BaseFragment
import com.example.mykotlinapp.util.ext.addFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), ItemButonRecyclerviewListener, SearchOnClickListener {
    val viewModel: HomeViewModel by viewModel()
    val inforViewModel : InformationViewModel by activityViewModels()

    var sortDesc = true
    var SortClick = false

    var page: Int = 1
    var per_page: Int = 10
    var currentItem: Int = 0
    var totalItems: Int = 0
    var scrollOutItems: Int = 0
    var isScrolling = false

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
        progressDialog.show()
        viewModel.getLichDESC(1, 10)
//        viewModel.getLichASC(1, 10)
        error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
//            Snackbar.make(binding.tvRcview, it.toString(), Snackbar.LENGTH_SHORT).show()
        }
        lichgonracDESC.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            adapterList?.setData(it)
            adapterGird?.setData(it)
            page++

        }


        isSuccess.observe(viewLifecycleOwner)
        {
            progressDialog.dismiss()
            if(it){
                loginID.observe(viewLifecycleOwner){
                    inforViewModel.getLoginID(it)
                    onHideSoftKeyBoard()
                    MainActivity.addNewFragment(MainActivity.mInforfragment)
                    val fragment = MainActivity.mInforfragment
                    MainActivity.navigateFragment(fragment)

                }
            }
            else
            {
                ShowDialog().showDialogSearch(requireActivity())
            }
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

        registerLiveData()
        sortDesc = true

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

        binding!!.scrollView.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            val scrollY = binding!!.scrollView.scrollY * 2 / 1000.toFloat() // For ScrollView
            val scrollX = binding!!.scrollView.scrollX / 1000.toFloat()// For HorizontalScrollView
            // DO SOMETHING WITH THE SCROLL COORDINATES
//            Log.d("scroll", "x top = $scrollX y = $scrollY")
//            binding.tvScroll.setText("Scroll = " + isScrolling + " - " + page + " => " + binding!!.scrollView.scrollY  + " - " + binding!!.recyclerview.bottom  + " - " + binding!!.layoutListBottom.bottom + " - " + binding!!.scrollView.height )

            if (binding.scrollView.scrollY < 600) {
                binding.view2.alpha = 1.toFloat() - scrollY
                binding.view2.translationY = -scrollY * 300
                binding.view3.alpha = scrollY
            }

            if (currentItem > scrollOutItems)
                scrollOutItems = currentItem
            currentItem = binding!!.scrollView.scrollY
            totalItems = binding!!.recyclerview.bottom - (binding!!.scrollView.height - binding!!.layoutListBottom.bottom)
//            binding.tvScroll.setText("""Scroll = $isScrolling - $page => ${currentItem} - ${totalItems} - ${binding!!.layoutListBottom.bottom} - ${binding!!.scrollView.height}""")

            if (isScrolling && currentItem >= totalItems && currentItem > scrollOutItems) {
                progressDialog.show()
                isScrolling = false
                Handler().postDelayed(Runnable {
                    if (sortDesc) {
                        viewModel.getLichDESC(page, 10)
                        viewModel.error.observe(viewLifecycleOwner) {
                            progressDialog.dismiss()
                            Snackbar.make(binding.tvRcview, it.toString(), Snackbar.LENGTH_SHORT).show()
                        }
                        viewModel.lichgonracDESC.observe(viewLifecycleOwner) {
                            progressDialog.dismiss()
                            adapterList?.addDatalist(it)
                            adapterGird?.addData(it)
                            newRecyclerView.adapter?.notifyDataSetChanged()
                            page++
                        }
                    }
                    else
                    {
                        viewModel.getLichASC(page, 10)
                        viewModel.error.observe(viewLifecycleOwner) {
                            progressDialog.dismiss()
                            Snackbar.make(binding.tvRcview, it.toString(), Snackbar.LENGTH_SHORT).show()
                        }
                        viewModel.lichgonracASC.observe(viewLifecycleOwner) {
                            progressDialog.dismiss()
                            adapterList?.addDatalist(it)
                            adapterGird?.addData(it)
                            newRecyclerView.adapter?.notifyDataSetChanged()
                            page++
//                            Toast.makeText(
//                                context,
//                                "is Scroll currentItem bottom" + page,
//                                Toast.LENGTH_SHORT
//                            ).show()
                        }
                    }

                }, 2000)
            }
        })


        //recyclerView
        adapterList = AdapterListCollectionSchedule()
        adapterGird = AdapterGridCollectionSchedule()
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
                viewModel.getLichASC(1, 10)
                viewModel.getLichDESC(1, 10)
                if (!sortDesc) {
                    progressDialog.show()
                    viewModel.lichgonracASC.observe(viewLifecycleOwner) {
                        progressDialog.dismiss()
                        adapterList?.setData(it)
                        adapterGird?.setData(it)
                        page = 2
                    }
                } else {
                    progressDialog.show()
                    viewModel.lichgonracDESC.observe(viewLifecycleOwner) {
                        progressDialog.dismiss()
                        adapterList?.setData(it)
                        adapterGird?.setData(it)
                        page = 2
                    }
                }
            }, 1000)
            scrollOutItems = 0
        }
        //swipeRefreshLayout custom color
        binding.swipeRefreshLayout.setColorSchemeResources(
            R.color.grac_green,
            R.color.grac_orange,
            R.color.purple_200
        )
    }

    private fun initCtrl() {

        binding.appToolbar.setOnSearchListener(this)

        binding.root.bt_price.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(activity, GarbagePriceActivity::class.java)
//                intent.putExtra(
//                    "urlprice",
//                    "https://grac.vn/category/gia-tien-rac/"
//                )
                startActivity(intent)
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


        newRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        binding.scrollView.setOnClickListener {
            onHideSoftKeyBoard()
        }

    }

    override fun onListClicked() {
        changeWhiteButon()
        SortClick = false
        sortDesc = true
        binding.ivList.setImageResource(R.drawable.ic_list_green)
        progressDialog.show()
        viewModel.getLichDESC(1, 10)
        viewModel.lichgonracDESC.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            adapterList?.setData(it)
            adapterGird?.setData(it)
            progressDialog.dismiss()
            page = 2
        }
        viewModel.error.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            Snackbar.make(binding.tvRcview, it.toString(), Snackbar.LENGTH_SHORT).show()
        }
        newRecyclerView.layoutManager = GridLayoutManager(context, 1)
        newRecyclerView.adapter = adapterList

        scrollOutItems = 0
    }

    override fun onMapClicked() {
        SortClick = false
//        changeWhiteButon()
//        binding.ivMap.setImageResource(R.drawable.ic_map_green)
//        val uri = Uri.parse("geo:10.830401793227432,106.65107973585354?z=12")
//        val location = Intent(Intent.ACTION_VIEW, uri)
//        location.setPackage("com.google.android.apps.maps")
//        startActivity(location)

//        if (location.resolveActivity(requireActivity().packageManager) != null) {
//            startActivity(location)
//        } else {
//            Toast.makeText(requireContext(),"No application can handle the link",Toast.LENGTH_SHORT).show()
//        }

        startActivity(Intent(activity, MapsActivity::class.java))

    }

    override fun onSortClicked() {
        changeWhiteButon()
        viewModel.getLichASC(1, 10)
        viewModel.getLichDESC(1, 10)
        if (sortDesc && !SortClick) {
            binding.ivSort.setImageResource(R.drawable.ic_sort_green)
            sortDesc = true
            SortClick = true
            viewModel.lichgonracDESC.observe(viewLifecycleOwner) {
                progressDialog.dismiss()
                adapterList?.setData(it)
                adapterGird?.setData(it)
            }
        }
        else if (sortDesc && SortClick) {
            binding.ivSort.setImageResource(R.drawable.ic_sort_up_green)
            sortDesc = false
            SortClick = true
            viewModel.lichgonracASC.observe(viewLifecycleOwner) {
                progressDialog.dismiss()
                adapterList?.setData(it)
                adapterGird?.setData(it)
            }
        }else {
            binding.ivSort.setImageResource(R.drawable.ic_sort_green)
            sortDesc = true
            SortClick = true
            viewModel.lichgonracDESC.observe(viewLifecycleOwner) {
                progressDialog.dismiss()
                adapterList?.setData(it)
                adapterGird?.setData(it)
            }
        }
        newRecyclerView.layoutManager = GridLayoutManager(context, 1)
        newRecyclerView.adapter = adapterList
        page = 2
        scrollOutItems = 0
    }

    override fun onGridClicked() {
        SortClick = false
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

    override fun onSearchClicked(text: String?) {
        onHideSoftKeyBoard()
        progressDialog.show()
        if (text != null) {
            viewModel.getLoginID(text)
        }
    }
}