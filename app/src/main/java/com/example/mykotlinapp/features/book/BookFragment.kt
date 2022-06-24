package com.example.mykotlinapp.features.book

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityComplainBinding
import com.example.mykotlinapp.databinding.FragmentBookBinding
import com.example.mykotlinapp.features.search.InformationFragment
import com.example.mykotlinapp.features.search.SearchFragment
import com.example.mykotlinapp.util.ext.addFragment

class BookFragment : Fragment() , BookOnclickListenner {


    private lateinit var binding: FragmentBookBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookBinding.inflate(inflater)

        binding.book = this

        return binding.root
    }

    override fun bookBackOnClick() {

    }

    override fun bookQuestionOnClick() {
        val customDialog = Dialog(requireContext())
        customDialog.setContentView(R.layout.dialog_updating)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val ivClose = customDialog.findViewById<ImageView>(R.id.iv_close)
        ivClose.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    override fun bookNextPageOnClick() {
//        val frament: Fragment = BookNextPageFragment()
//        val transaction = requireFragmentManager().beginTransaction()
//        transaction.replace(R.id.container, frament)
//        transaction.addToBackStack(null)
//        transaction.commit()
        addFragment(R.id.container, BookNextPageFragment.newInstance())
    }

    override fun bookRacThaiSinhHoatOnClick() {
        val customDialog = Dialog(requireContext())
        customDialog.setContentView(R.layout.dialog_rac_sinhhoat)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val ivClose = customDialog.findViewById<ImageView>(R.id.iv_close)
        ivClose.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    override fun bookRacThaiDacBietOnClick() {
        val customDialog = Dialog(requireContext())
        customDialog.setContentView(R.layout.dialog_rac_dacbiet)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val ivClose = customDialog.findViewById<ImageView>(R.id.iv_close)
        ivClose.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }


}