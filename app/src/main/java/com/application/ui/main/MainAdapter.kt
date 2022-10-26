package com.application.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.application.R
import com.application.data.remote.response.Data
import com.application.ui.gallery.GalleryViewActivity
import com.application.utils.common.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_gallery.view.*

class MainAdapter(
    var galleryData: List<Data?>,
    val context: Context,
) :
    RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapterViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_gallery, parent, false
        )
        return MainAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MainAdapterViewHolder,
        position: Int
    ) {


        holder.itemView.text_title.text =
            galleryData[position]!!.title.toString()

        Glide.with(context)
            .load(galleryData[position]!!.url)
            .placeholder(
                R.mipmap.ic_launcher
            ).into(holder.itemView.image_url)
        holder.itemView.cardview_image.setOnClickListener {
            val intent = Intent(context, GalleryViewActivity::class.java)
            Constants.position = holder.layoutPosition
            context.startActivity(intent)
        }
    }

    fun onAddItems(list: List<Data?>) {
        this.galleryData = list
        notifyDataSetChanged()
    }

    fun onItemRefresh(pos: Int) {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (galleryData == null) return 0
        return galleryData.size

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    inner class MainAdapterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)
}

