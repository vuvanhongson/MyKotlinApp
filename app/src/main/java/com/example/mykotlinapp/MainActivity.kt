package com.example.mykotlinapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mykotlinapp.common.BottomBar
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.ActivityMainBinding
import com.example.mykotlinapp.features.CurrentTabActive
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.book.BookNextPageFragment
import com.example.mykotlinapp.features.home.HomeFragment
import com.example.mykotlinapp.features.question.QuestionFragment
import com.example.mykotlinapp.features.search.InformationFragment
import com.example.mykotlinapp.features.search.SearchFragment
import com.example.mykotlinapp.features.support.ContactFragment
import com.example.mykotlinapp.features.support.SupportFragment
import com.example.mykotlinapp.features.wallet.WalletFragment

class MainActivity : AppCompatActivity(), BottomItemChangedListener {

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
    }

    private fun initBottomBar() {

        mFragmentManager!!.beginTransaction().add(
            binding!!.container.id,
            mContact, BottomBar.SUPPORT
        ).hide(mContact).commit()

//        mFragmentManager!!.beginTransaction().add(
//            binding!!.container.id,
//            mBookNextPageFragment, BottomBar.BOOKING
//        ).hide(mBookNextPageFragment).commit()

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
        CurrentTabActive.CURRENTTAB = mHomeFragment
    }


    override fun onBottomItemClicked(type: String?) {
        mBookNextPageFragment = BookNextPageFragment()
        mSearch = SearchFragment()
        mInforfragment = InformationFragment()
        when (type) {
            BottomBar.HOME -> {
//                onBackPressed()
//                mFragmentManager!!.beginTransaction().hide(mCurrentTabActive!!).show(mHomeFragment)
//                    .commit()
                navigateFragment(mHomeFragment)
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

        private lateinit var binding: ActivityMainBinding

        val mHomeFragment = HomeFragment()
        val mSupport = SupportFragment()
        val mQuestion = QuestionFragment()
        val mBook = BookFragment()
        val mWallet = WalletFragment()
        val mContact = ContactFragment()
        var mBookNextPageFragment = BookNextPageFragment()
        var mSearch = SearchFragment()
        var mInforfragment = InformationFragment()


        var mCurrentTabActive: Fragment? = null
        var mFragmentManager: FragmentManager? = null

        fun addNewFragment(fragment: Fragment)
        {
            mFragmentManager!!.beginTransaction().add(
                binding!!.container.id,
                fragment, BottomBar.SUPPORT
            ).hide(fragment).commit()
        }

        fun removeFragment(fragment: Fragment)
        {
            mFragmentManager!!.beginTransaction().remove(fragment).commit()
        }

        fun navigateFragment(fragment: Fragment) {
            mFragmentManager!!.beginTransaction().addToBackStack(fragment::class.java.simpleName).hide(mCurrentTabActive!!).show(fragment).commit()

            mCurrentTabActive = fragment
            CurrentTabActive.CURRENTTAB = fragment
            Log.d("hello", "back Fragment")
        }
    }
}