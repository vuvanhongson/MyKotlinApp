package com.example.mykotlinapp.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

        binding.backSearch.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val items =
            listOf("An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Cạn", "Bạc Liêu", "Bắc Ninh")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, items)
        binding.dropdownMenu.setAdapter(adapter)

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