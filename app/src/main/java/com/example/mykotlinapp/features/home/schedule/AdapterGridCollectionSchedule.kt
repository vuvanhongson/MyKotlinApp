package com.example.mykotlinapp.features.home.schedule

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapp.R
import com.example.mykotlinapp.data.model.LichGomRac

class AdapterGridCollectionSchedule() :
    RecyclerView.Adapter<AdapterGridCollectionSchedule.MyViewHolder>() {

    private var lichGom = mutableListOf<LichGomRac>()

    fun setData(lichGom: MutableList<LichGomRac>) {
        this.lichGom.clear()
        this.lichGom = lichGom
        lichGom.addAll(lichGom)
        notifyDataSetChanged()
        Log.d("lichGom", " x - " + lichGom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_home_grid, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currenItem = lichGom[position]
        holder.titleImage.setImageResource(R.drawable.item_ban)
        holder.tvName.text = "Người dùng"
        holder.tvAddress.text = "Thành phố Hồ Chí Minh"
        holder.tvDate.text = currenItem.ngayDang

    }

    override fun getItemCount(): Int {
        return lichGom.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ImageView = itemView.findViewById(R.id.img_item_recycler)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvAddress: TextView = itemView.findViewById(R.id.tv_address)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)

    }
}