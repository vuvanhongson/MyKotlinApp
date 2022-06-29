package com.example.mykotlinapp.features.book

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.MainActivity
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.BottomBar
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.FragmentBookBinding
import com.example.mykotlinapp.features.CurrentTabActive
import com.example.mykotlinapp.util.ext.addFragment
import kotlinx.android.synthetic.main.fragment_book.*

class BookFragment : Fragment(), BookOnclickListenner {
    private lateinit var binding: FragmentBookBinding
    var nametoolbar: String = ""
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
        BottomBar.HOME
    }

    override fun bookQuestionOnClick() {
        ShowDialog().showDialog(requireContext())
    }

    override fun bookNextPageOnClick() {
        when {
            radioButton1.isChecked -> nametoolbar = binding.radioButton1.text.toString()
            radioButton2.isChecked -> nametoolbar = binding.radioButton2.text.toString()
            radioButton3.isChecked -> nametoolbar = binding.radioButton3.text.toString()
            radioButton4.isChecked -> nametoolbar = binding.radioButton4.text.toString()
            radioButton5.isChecked -> nametoolbar = binding.radioButton5.text.toString()
            radioButton6.isChecked -> nametoolbar = binding.radioButton6.text.toString()
            radioButton7.isChecked -> nametoolbar = binding.radioButton7.text.toString()
            radioButton8.isChecked -> nametoolbar = binding.radioButton8.text.toString()
            radioButton9.isChecked -> nametoolbar = binding.radioButton9.text.toString()
            radioButton10.isChecked -> nametoolbar = binding.radioButton10.text.toString()
        }
        val bundle = Bundle()
        bundle.putString("data", nametoolbar)
        val fragment = BookNextPageFragment()
        fragment.arguments = bundle
        addFragment(R.id.container, fragment)
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