package com.example.mykotlinapp.features.complain

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.databinding.ActivityComplainBinding
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding
import com.example.mykotlinapp.features.search.InformationFragment
import kotlinx.android.synthetic.main.activity_complain.*


class ComplainActivity : AppCompatActivity(), ComplainOnClickListenner {

    private lateinit var binding: ActivityComplainBinding
    var nameComplain : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))

        super.onCreate(savedInstanceState)
        binding = ActivityComplainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.complain = this

    }

    override fun backOnclick() {
        onBackPressed()
    }

    override fun questionOnclick() {
        ShowDialog().showDialog(this)
    }

    override fun nextPageOnclick() {
        val i = Intent(this@ComplainActivity, ComplainNextPageActivity::class.java)
        when{
            radioButton1.isChecked -> nameComplain = radioButton1.text.toString()
            radioButton2.isChecked -> nameComplain = radioButton2.text.toString()
            radioButton3.isChecked -> nameComplain = radioButton3.text.toString()
            radioButton4.isChecked -> nameComplain = radioButton4.text.toString()
            radioButton5.isChecked -> nameComplain = radioButton5.text.toString()

        }
        i.putExtra("namecomplain", nameComplain)
        startActivity(i)
    }
}








