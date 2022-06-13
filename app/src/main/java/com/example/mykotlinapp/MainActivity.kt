package com.example.mykotlinapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.mykotlinapp.databinding.ActivityMainBinding
import com.example.mykotlinapp.databinding.LayoutViewToolbarBinding
import com.example.mykotlinapp.features.slide.ViewPageAdapter
import me.relex.circleindicator.CircleIndicator
import org.koin.android.ext.android.get
import kotlin.properties.Delegates

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
    }
}