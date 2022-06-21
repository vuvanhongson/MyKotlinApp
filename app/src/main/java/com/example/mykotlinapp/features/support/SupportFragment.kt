package com.example.mykotlinapp.features.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.BottomBar
import com.example.mykotlinapp.common.listener.BottomItemChangedListener
import com.example.mykotlinapp.databinding.FragmentHomeBinding
import com.example.mykotlinapp.databinding.FragmentSupportBinding
import com.example.mykotlinapp.features.book.BookFragment
import com.example.mykotlinapp.features.book.BookNextPageFragment


class SupportFragment : Fragment() {

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



        binding!!.btSupport.setOnClickListener{
//            val frament: Fragment = BookFragment()
//            fragmentManager
//                ?.beginTransaction()
//                ?.replace(R.id.container, frament)
//                ?.commit()
            //Create new fragment and transaction
            val newFragment: Fragment = ContactFragment()
            // consider using Java coding conventions (upper first char class names!!!)
            val transaction = requireFragmentManager().beginTransaction()

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.container, newFragment)
            transaction.addToBackStack(null)

            // Commit the transaction
            transaction.commit()

        }

        binding.ivBackSupport.setOnClickListener{
            mBottomItemChangedListener!!.onBottomItemClicked(BottomBar.HOME)
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}