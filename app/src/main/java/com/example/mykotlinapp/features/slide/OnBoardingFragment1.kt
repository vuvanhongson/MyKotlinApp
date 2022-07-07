package com.example.mykotlinapp.features.slide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentOnboarding1Binding
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.search.SearchFragment
import com.example.mykotlinapp.util.ext.addFragment

class OnBoardingFragment1 : Fragment() {

    private lateinit var binding: FragmentOnboarding1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboarding1Binding.inflate(inflater)
        binding!!.btnSearch.setOnClickListener{
//            val frament: Fragment = SearchFragment()
//            val transaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.container, frament)
//            transaction.addToBackStack(null)
//            transaction.commit()
            addFragment(R.id.container, SearchFragment.newInstance())
        }
        binding.book.setOnClickListener {
            addFragment(R.id.container, BookFragment.newInstance())
        }
        return binding.root
    }
}