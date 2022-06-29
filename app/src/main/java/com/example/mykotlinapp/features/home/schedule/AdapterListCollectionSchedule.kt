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
import com.example.mykotlinapp.util.ext.loadImage
import kotlinx.android.synthetic.main.list_item_home.view.*
import kotlinx.android.synthetic.main.list_item_home_grid.view.*

class AdapterListCollectionSchedule() :
    RecyclerView.Adapter<AdapterListCollectionSchedule.MyViewHolder>() {

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
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(lichGom[position])

    }

    override fun getItemCount(): Int {
        return lichGom.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(lichGom: LichGomRac) {
            try{
                itemView.img_item_list.loadImage(lichGom?.picture1)
            }catch (e : Exception)
            {
                itemView.img_item_list.setImageResource(R.drawable.ic_chonhinhanh)
            }
            itemView.tv_name_list.text = lichGom.tenKhachHang
            itemView.tv_address_list.text = lichGom.diaChi
            itemView.tv_date_list.text = lichGom.ngayDang
        }
    }
}