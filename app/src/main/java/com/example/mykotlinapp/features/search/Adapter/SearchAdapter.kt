package com.example.mykotlinapp.features.search.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mykotlinapp.R
import com.example.mykotlinapp.data.model.AddressProvince

class SearchAdapter(
    context: Context, resource: Int, objects: MutableList<AddressProvince>
) : ArrayAdapter<AddressProvince>(context, resource, objects) {

    var itemTinhClick: ((AddressProvince) -> Unit)? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_dropdown, parent, false)
        }
        val textView = convertView!!.findViewById<TextView>(R.id.tv_dropdown_tinhtp)
        val addressProvince = getItem(position)
        textView.text = addressProvince!!.fullName

        textView.setOnClickListener {
            itemTinhClick!!.invoke(addressProvince)
        }

        textView.setTextIsSelectable(true)
        textView.isFocusable = false
        textView.isFocusableInTouchMode = false

        return convertView
    }
}