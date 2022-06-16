package com.example.mykotlinapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.ActivityMainBinding
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.home.HomeFragment
import com.example.mykotlinapp.features.question.QuestionFragment
import com.example.mykotlinapp.features.slide.ViewPageAdapter
import com.example.mykotlinapp.features.support.SupportFragment
import com.example.mykotlinapp.features.wallet.WalletFragment

class MainActivity : AppCompatActivity() , BottomItemChangedListener {
//    private var binding: LayoutViewToolbarBinding

    private val mHomeFragment = HomeFragment()
    private val mSupport = SupportFragment()
    private val mQuestion = QuestionFragment()
    private val mBook = BookFragment()
    private val mWallet= WalletFragment()

    private var mCurrentTabActive: Fragment? = null
    private var mFragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        window.statusBarColor = Color.TRANSPARENT

        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onBottomItemClicked(type: String?) {
        TODO("Not yet implemented")
    }
}