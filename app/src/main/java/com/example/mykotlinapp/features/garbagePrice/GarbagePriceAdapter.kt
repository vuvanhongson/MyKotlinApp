package com.example.mykotlinapp.features.garbagePrice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapp.R

class GarbagePriceAdapter(private val newsList: ArrayList<GarbagePrice>) : RecyclerView.Adapter<GarbagePriceAdapter.MyViewHolder>(){

    var itemTinhClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currenItem = newsList[position]
//        holder.imgTitleHeader.setImageResource(currenItem.imgTitleHeader)
        holder.imgBackgroundHeader.setImageResource(currenItem.imgBackgroundHeader)
        holder.tvTitleHeader.text=currenItem.tvTitleHeader
        holder.tvTitleCenter.text=currenItem.tvTitleCenter
        holder.tvTitleCenter2.text=currenItem.tvTitleCenter2
        holder.tvContent.text=currenItem.tvContent

            holder.itemclick.setOnClickListener {
                itemTinhClick!!.invoke(position)
            }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgTitleHeader : ImageView = itemView.findViewById(R.id.img_date_header)
        var imgBackgroundHeader: ImageView = itemView.findViewById(R.id.img_bg_header)
        var tvTitleHeader : TextView = itemView.findViewById(R.id.tv_title_header)
        var tvTitleCenter : TextView = itemView.findViewById(R.id.tv_title_center)
        var tvTitleCenter2 : TextView = itemView.findViewById(R.id.tv_title_center2)
        var tvContent : TextView = itemView.findViewById(R.id.tv_content)
        var tvXemThem : TextView = itemView.findViewById(R.id.tv_xemthem)
        var itemclick: ConstraintLayout = itemView.findViewById(R.id.itembackground)
    }

}