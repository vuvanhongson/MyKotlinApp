package com.example.mykotlinapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mykotlinapp.common.BottomBar
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.ActivityMainBinding
import com.example.mykotlinapp.features.CurrentTabActive
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.home.HomeFragment
import com.example.mykotlinapp.features.question.QuestionFragment
import com.example.mykotlinapp.features.support.SupportFragment
import com.example.mykotlinapp.features.wallet.WalletFragment

class MainActivity : AppCompatActivity(), BottomItemChangedListener {
    private lateinit var binding: ActivityMainBinding

    private val mHomeFragment = HomeFragment()
    private val mSupport = SupportFragment()
    private val mQuestion = QuestionFragment()
    private val mBook = BookFragment()
    private val mWallet= WalletFragment()


    override fun onCreate(savedInstanceState: Bundle?) {

        window.statusBarColor = Color.TRANSPARENT

        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//  set status text dark


        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.container, BookFragment()).commit()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initControls()
//        initListeners()
        initBottomBar()
    }

    private fun initListeners() {
            mFragmentManager!!.beginTransaction().hide(mCurrentTabActive!!).show(mHomeFragment)
            mCurrentTabActive = mHomeFragment
            binding!!.bottomBar.onHomeClicked()
    }

    private fun initControls() {
        mFragmentManager = supportFragmentManager
        CurrentTabActive.FRAM = mFragmentManager
        binding!!.bottomBar.setOnBottomItemChangedListener(this)
        mSupport.setOnBottomItemChangedListener(this)
    }

    private fun initBottomBar() {
        mFragmentManager!!.beginTransaction().add(
            binding!!.container.id,
            mSupport, BottomBar.SUPPORT
        ).hide(mSupport).commit()

        mFragmentManager!!.beginTransaction().add(
            binding!!.container.id,
            mBook, BottomBar.BOOKING
        ).hide(mBook).commit()

        mFragmentManager!!.beginTransaction().add(
            binding!!.container.id,
            mQuestion, BottomBar.QUESTION
        ).hide(mQuestion).commit()

        mFragmentManager!!.beginTransaction().add(
            binding!!.container.id,
            mWallet, BottomBar.WALLET
        ).hide(mWallet).commit()

        mFragmentManager!!.beginTransaction().add(
            binding!!.container.id,
            mHomeFragment, BottomBar.HOME
        ).commit()

        mCurrentTabActive = mHomeFragment
    }

    private fun navigateFragment(fragment: Fragment) {
        mFragmentManager!!.beginTransaction().hide(mCurrentTabActive!!).show(fragment).commit()
        mCurrentTabActive = fragment
        CurrentTabActive.CURRENTTAB = fragment
    }


    override fun onBottomItemClicked(type: String?) {
        when (type) {
            BottomBar.HOME -> {
                mFragmentManager!!.beginTransaction().hide(mCurrentTabActive!!).show(mHomeFragment)
                    .commit()
                mCurrentTabActive = mHomeFragment
                CurrentTabActive.CURRENTTAB = mHomeFragment
            }
            BottomBar.SUPPORT -> {
                navigateFragment(mSupport)
            }
            BottomBar.BOOKING -> {
                navigateFragment(mBook)
            }
            BottomBar.QUESTION -> {
                navigateFragment(mQuestion)
            }
            BottomBar.WALLET -> {
                navigateFragment(mWallet)
            }
        }
    }

    companion object {
        private var mCurrentTabActive: Fragment? = null
        private var mFragmentManager: FragmentManager? = null

        fun backFragment(current: Fragment, new : Fragment){
            mFragmentManager!!.beginTransaction().hide(current!!).show(new)
                .commit()
            mCurrentTabActive = new
            CurrentTabActive.CURRENTTAB = new
        }
    }
}