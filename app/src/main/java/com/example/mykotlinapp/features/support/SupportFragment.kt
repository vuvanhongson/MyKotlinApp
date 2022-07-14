package com.example.mykotlinapp.features.support

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.common.BottomBar
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.FragmentSupportBinding
import com.example.mykotlinapp.features.CurrentTabActive
import com.example.mykotlinapp.features.complain.ComplainActivity
import com.example.mykotlinapp.util.ext.replaceChildFragment


class SupportFragment : Fragment() , SupportItemClick{


    private lateinit var binding: FragmentSupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSupportBinding.inflate(inflater)

        binding.ivBackSupport.setOnClickListener{
//            mBottomItemChangedListener!!.onBottomItemClicked(BottomBar.HOME)
        }

        binding.support = this


        return binding.root
    }

    override fun Support1onClick() {
//        val newFragment: Fragment = ContactFragment()
//        val transaction = CurrentTabActive.FRAM!!.beginTransaction()
//        transaction.replace(R.id.container, newFragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
        MainActivity.navigateFragment(MainActivity.mContact)
    }

    override fun Support2onClick() {
        startActivity(Intent(requireActivity(), ComplainActivity::class.java))
    }

    override fun Support3onClick() {
        MainActivity.backBook()
        MainActivity.navigateFragment(MainActivity.mBook)
    }

    override fun Support4onClick() {
        MainActivity.navigateFragment(MainActivity.mQuestion)
    }

    override fun back() {
        MainActivity.backHome()
        MainActivity.navigateFragment(MainActivity.mHomeFragment)
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