package com.example.mykotlinapp.features.home.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapp.R

class AdapterGridCollectionSchedule (private val workList: ArrayList<Schedule>) :
    RecyclerView.Adapter<AdapterGridCollectionSchedule.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_home_grid, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currenItem = workList[position]
        holder.titleImage.setImageResource(currenItem.titleImage)
        holder.tvName.text = currenItem.tvName
        holder.tvAddress.text = currenItem.tvAddress
        holder.tvDate.text = currenItem.tvDate

    }

    override fun getItemCount(): Int {
        return workList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ImageView = itemView.findViewById(R.id.img_item_recycler)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvAddress: TextView = itemView.findViewById(R.id.tv_address)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)

    }
}