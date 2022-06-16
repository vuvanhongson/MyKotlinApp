package com.example.mykotlinapp.features.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentHomeBinding
import com.example.mykotlinapp.features.schedule.AdapterCollectionSchedule
import com.example.mykotlinapp.features.schedule.Schedule
import com.example.mykotlinapp.features.slide.ViewPageAdapter

class HomeFragment : Fragment() {

    //recyclerview
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Schedule>
    lateinit var imageWork: Array<Int>
    lateinit var nameWork: Array<String>
    lateinit var addressWork: Array<String>
    lateinit var dateWork: Array<String>


    //    private var binding: LayoutViewToolbarBinding
    private var viewPageAdapter: ViewPageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        val binding = FragmentHomeBinding.inflate(inflater)


        viewPageAdapter = ViewPageAdapter(
            parentFragmentManager!!,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        binding.viewPager.setAdapter(viewPageAdapter)
        binding.circleIndicartor.setViewPager( binding.viewPager)
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

        binding.btPrice!!.setOnClickListener{
            val x = binding.scrollView.scrollY
            Log.d("scroll", " x - " + x)
        }

        binding!!.scrollView.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            val scrollY = binding!!.scrollView.scrollY*2/1000.toFloat() // For ScrollView
            val scrollX = binding!!.scrollView.scrollX/1000.toFloat()// For HorizontalScrollView
            // DO SOMETHING WITH THE SCROLL COORDINATES
//            Log.d("scroll", "x top = $scrollX y = $scrollY")

            if(binding.scrollView.scrollY < 600)
            {
//            binding.view2.animate().alphaBy(1.0.toFloat())
//                .alpha(1.toFloat() - scrollY).duration = 100
                binding.view2.alpha = 1.toFloat() - scrollY
                binding.view2.translationY = -scrollY*300

//            binding.view3.animate().alphaBy(0.0.toFloat())
//                .alpha(scrollY).duration = 100
                binding.view3.alpha = scrollY
            }

            if(binding.scrollView.scrollY > 400)
            {
                binding.appToolbar.changeColorIcon(0)
                requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//  set status text dark
            }
            else
            {
                binding.appToolbar.changeColorIcon(1)
                requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//  set status text dark
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



        return binding.root
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

}