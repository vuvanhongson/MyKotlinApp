package com.example.mykotlinapp.features.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.BottomBar
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.FragmentHomeBinding
import com.example.mykotlinapp.databinding.FragmentSupportBinding
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.book.BookNextPageFragment


class SupportFragment : Fragment() , SupportItemClick{

    private var mBottomItemChangedListener: BottomItemChangedListener? = null

    private lateinit var binding: FragmentSupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun setOnBottomItemChangedListener(bottomItemChangedListener: BottomItemChangedListener?) {
        mBottomItemChangedListener = bottomItemChangedListener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSupportBinding.inflate(inflater)

//        binding!!.btSupport.setOnClickListener{
//            val newFragment: Fragment = ContactFragment()
//            val transaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.container, newFragment)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }

        binding.ivBackSupport.setOnClickListener{
            mBottomItemChangedListener!!.onBottomItemClicked(BottomBar.HOME)
        }

        binding.support = this

        return binding.root
    }

    override fun Support1onClick() {
        val newFragment: Fragment = ContactFragment()
        val transaction = requireFragmentManager().beginTransaction()
        transaction.replace(R.id.container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun Support2onClick() {
    }

    override fun Support3onClick() {
    }


}