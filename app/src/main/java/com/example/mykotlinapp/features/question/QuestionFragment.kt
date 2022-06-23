package com.example.mykotlinapp.features.question

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.ActivityDumpTrashBinding
import com.example.mykotlinapp.databinding.FragmentQuestionBinding
import com.example.mykotlinapp.features.games.GameWebViewVerticalActivity

class QuestionFragment : Fragment() , QuestionItemClick {

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

        binding.question = this

        return binding.root
    }

    override fun Question1onClick() {
        val intent = Intent(activity, QuestionWebViewActivity::class.java)
        intent.putExtra(
            "urlquestion",
            "https://grac.vn/cau-hoi-thuong-gap/"
        )
        startActivity(intent)
    }

    override fun Question2onClick() {
        val intent = Intent(activity, QuestionWebViewActivity::class.java)
        intent.putExtra(
            "urlquestion",
            "https://grac.vn/cau-hoi-thuong-gap/"
        )
        startActivity(intent)
    }

    override fun Question3onClick() {
        val intent = Intent(activity, QuestionWebViewActivity::class.java)
        intent.putExtra(
            "urlquestion",
            "https://grac.vn/cau-hoi-thuong-gap/"
        )
        startActivity(intent)
    }

}