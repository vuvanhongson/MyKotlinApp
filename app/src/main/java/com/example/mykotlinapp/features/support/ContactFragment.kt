package com.example.mykotlinapp.features.support

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mykotlinapp.R
import com.example.mykotlinapp.databinding.FragmentContactBinding
import com.example.mykotlinapp.features.home.HomeFragment
import com.example.mykotlinapp.util.ext.addFragment
import com.example.mykotlinapp.util.ext.replaceChildFragment


class ContactFragment : Fragment(), ContactItemClick {

    private lateinit var binding: FragmentContactBinding
    val phoneNumber = "19000340"
    val zalo = "https://zalo.me/0908090013"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContactBinding.inflate(inflater)


        binding.backContact.setOnClickListener {
            requireActivity().onBackPressed()
//            replaceChildFragment(R.id.container, SupportFragment.newInstance())
        }

        binding.contact = this

        return binding.root
    }

    override fun Contact1onClick() {

        if (Build.VERSION.SDK_INT > 23) {
            startCall()
        } else {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.CALL_PHONE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    requireContext(),
                    "Hãy cấp quyền cuộc gọi cho ứng dụng!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val PERMISSIONS_STORAGE = arrayOf<String>(android.Manifest.permission.CALL_PHONE)
                ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS_STORAGE, 9)
                startCall()
            }
        }
    }

    private fun startCall() {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:" + phoneNumber)
        startActivity(callIntent)
    }

    override fun Contact2onClick() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(zalo))
        startActivity(intent)
    }

    override fun Contact3onClick() {
        val gmail = Intent(Intent.ACTION_SEND)
        gmail.setType("message/rfc822")
            .putExtra(Intent.EXTRA_EMAIL, arrayOf("congnghe@grac.vn"))
            .putExtra(Intent.EXTRA_SUBJECT, "Yêu cầu hỗ trợ đến Grac")
            .putExtra(Intent.EXTRA_TEXT, "hãy nhập yêu cầu của bạn...")
            .setPackage("com.google.android.gm")

        if (gmail.resolveActivity(requireContext().packageManager) != null) {
            startActivity(gmail)
        } else {
            Toast.makeText(requireActivity(), "Gmail chưa được cài đặt!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun Contact4onClick() {
        val intent = Intent(requireContext(), WebGracActivity::class.java)
        intent.putExtra(
            "urllink",
            "https://grac.vn"
        )
        startActivity(intent)
    }

    companion object {
        fun newInstance() = ContactFragment().apply {
            arguments = bundleOf()
        }
    }


}