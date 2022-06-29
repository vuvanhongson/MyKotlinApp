package com.example.mykotlinapp.features.support

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.BottomBar
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.FragmentHomeBinding
import com.example.mykotlinapp.databinding.FragmentSupportBinding
import com.example.mykotlinapp.features.CurrentTabActive
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.book.BookNextPageFragment
import com.example.mykotlinapp.features.home.HomeFragment
import com.example.mykotlinapp.util.ext.addFragment
import com.example.mykotlinapp.util.ext.replaceChildFragment


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

//        binding.ivBackSupport.setOnClickListener{
//            mBottomItemChangedListener!!.onBottomItemClicked(BottomBar.HOME)
////            replaceChildFragment(R.id.container, HomeFragment.newInstance())
//        }

        binding.support = this

        return binding.root
    }

    override fun Support1onClick() {
//        val newFragment: Fragment = ContactFragment()
//        val transaction = CurrentTabActive.FRAM!!.beginTransaction()
//        transaction.replace(R.id.container, newFragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
        addFragment(R.id.container, ContactFragment.newInstance())
        CurrentTabActive.CURRENTTAB = ContactFragment.newInstance()
    }

    override fun Support2onClick() {
        ShowDialog().showDialog(requireContext())
    }

    override fun Support3onClick() {
        ShowDialog().showDialog(requireContext())
    }

    override fun back() {
        mBottomItemChangedListener!!.onBottomItemClicked(BottomBar.HOME)
    }

    override fun question() {
        ShowDialog().showDialog(requireContext())
    }

    companion object {
        fun newInstance() = SupportFragment().apply {
            arguments = bundleOf()
        }
    }

}