package com.example.mykotlinapp.features.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding
import com.example.mykotlinapp.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionBinding.inflate(inflater)


        return binding.root
    }

}