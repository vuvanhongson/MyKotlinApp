package com.example.mykotlinapp.features.book.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapp.R
import com.example.mykotlinapp.features.complain.ComplainNextPageActivity
import java.io.IOException

class PhotoBookAdapter(private val mContext: Context) :
    RecyclerView.Adapter<PhotoBookAdapter.PhotoBookViewHolder>() {

    private var mListPhotos: ArrayList<Uri>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<Uri>?) {
        mListPhotos = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoBookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_photo, parent, false)
        return PhotoBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoBookViewHolder, position: Int) {
        val uri = mListPhotos!![position]
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(mContext.contentResolver, uri)
            holder.imgPhoto.setImageBitmap(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        holder.imgDelete.setOnClickListener {
            mListPhotos!!.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mListPhotos!!.size)
        }
    }

    override fun getItemCount(): Int {
        return if (mListPhotos == null) {
            0
        } else {
            mListPhotos!!.size
        }
    }

    class PhotoBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView
        val imgDelete: CardView

        init {
            imgPhoto = itemView.findViewById(R.id.img_photo_select)
            imgDelete = itemView.findViewById(R.id.img_delete)
        }
    }
}


