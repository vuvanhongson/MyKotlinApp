package com.example.mykotlinapp.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        binding!!.btnKqSearch.setOnClickListener{
            val frament: Fragment = InformationFragment()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.container, frament)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return binding.root
    }
}