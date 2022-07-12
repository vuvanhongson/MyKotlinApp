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

    private var lichGomList = mutableListOf<LichGomRac>()

    fun setData(lichGom: MutableList<LichGomRac>) {
        this.lichGomList.clear()
        this.lichGomList = lichGom
//        lichGom.addAll(lichGom)
        notifyDataSetChanged()
//        Log.d("lichGomlIST", " x - " + lichGom)
//        Log.d("lichGomlISTsize", " x - " + lichGom.size)
    }
    fun addDatalist(lichGom2: MutableList<LichGomRac>) {
//        lichGomList.addAll(lichGom2)
//        notifyItemInserted(lichGomList.size)
        Log.d("lichGomlIST", " x - " + lichGomList)
//        Log.d("lichGomlISTsize", " x 1- " + lichGomList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(lichGomList[position])
//        Log.d("lichGomposition", " position - " + lichGomList[position].id)
    }

    override fun getItemCount(): Int {
//        Log.d("lichGomsize", " x - " + lichGomList.size)
        return lichGomList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(lichGom: LichGomRac) {
//            Log.d("lichGomid", " ID - " + lichGom.id)
            try{
                itemView.img_item_list.loadImage(lichGom?.picture1)
            }catch (e : Exception)
            {
                itemView.img_item_list.setImageResource(R.drawable.ic_khonghinhanh)
            }
            itemView.tv_name_list.text = lichGom.tenKhachHang
            itemView.tv_address_list.text = lichGom.diaChiThuGom
            itemView.tv_date_list.text = lichGom.ngayDang
        }
    }
}