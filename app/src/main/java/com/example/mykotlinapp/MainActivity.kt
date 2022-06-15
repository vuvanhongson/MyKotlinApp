package com.example.mykotlinapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.mykotlinapp.databinding.ActivityMainBinding
import com.example.mykotlinapp.features.slide.ViewPageAdapter

class MainActivity : AppCompatActivity() {
//    private var binding: LayoutViewToolbarBinding
    private var viewPageAdapter: ViewPageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPageAdapter = ViewPageAdapter(
            supportFragmentManager!!,
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
            Log.d("scroll", "x top = $scrollX y = $scrollY")

//            binding.view2.animate().alphaBy(1.0.toFloat())
//                .alpha(1.toFloat() - scrollY).duration = 100
            binding.view2.alpha = 1.toFloat() - scrollY
            binding.view2.translationY = -scrollY*300

//            binding.view3.animate().alphaBy(0.0.toFloat())
//                .alpha(scrollY).duration = 100
            binding.view3.alpha = scrollY
        })

    }
}