package com.example.mykotlinapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
    private val mWallet = WalletFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.TRANSPARENT
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )//  set status text dark
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.container, BookFragment()).commit()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initControls()
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
//                onBackPressed()
                mFragmentManager!!.beginTransaction().hide(mCurrentTabActive!!).show(mHomeFragment)
                    .commit()
                mCurrentTabActive = mHomeFragment
                CurrentTabActive.CURRENTTAB = mHomeFragment
            }
            BottomBar.SUPPORT -> {
//                onBackPressed()
                navigateFragment(mSupport)
            }
            BottomBar.BOOKING -> {
//                onBackPressed()
                navigateFragment(mBook)
            }
            BottomBar.QUESTION -> {
                onBackPressed()
                navigateFragment(mQuestion)
            }
            BottomBar.WALLET -> {
//                onBackPressed()
                navigateFragment(mWallet)
            }
        }
    }

//    override fun onBackPressed() {
//        val dialog1 = AlertDialog.Builder(this)
//        dialog1.setMessage("Bạn có muốn thoát ứng dụng?")
//        dialog1.setPositiveButton(
//            "Có"
//        ) { _, _ -> super.onBackPressed() }
//        dialog1.setNegativeButton(
//            "Không"
//        ) { _, _ -> }
//        dialog1.show()
//    }

    companion object {
        private var mCurrentTabActive: Fragment? = null
        private var mFragmentManager: FragmentManager? = null
        fun backFragment(current: Fragment, new: Fragment) {
            mFragmentManager!!.beginTransaction().hide(current!!).show(new)
                .commit()
            mCurrentTabActive = new
            CurrentTabActive.CURRENTTAB = new
        }
    }
}