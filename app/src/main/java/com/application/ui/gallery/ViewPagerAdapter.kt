package com.application.ui.gallery

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.application.R
import com.application.data.remote.response.Data
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_gallery.view.*
import kotlinx.android.synthetic.main.adapter_gallery.view.image_url
import kotlinx.android.synthetic.main.adapter_gallery.view.text_title
import kotlinx.android.synthetic.main.adapter_gallery_items.view.*
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerAdapter(val context: Context, val imageList: ArrayList<Data>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as CardView

    }

    @SuppressLint("ServiceCast")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View =
            mLayoutInflater.inflate(R.layout.adapter_gallery_items, container, false)
        //val imageView: ImageView = itemView.findViewById<View>(R.id.image_url) as ImageView
        itemView.text_title.text =
            imageList[position]!!.title.toString()
        itemView.text_date.text =
            imageList[position]!!.date.toString()
        itemView.text_copyright.text =
            imageList[position]!!.copyright.toString().plus(" ")
        itemView.text_explanation.text =
            imageList[position]!!.explanation.toString().plus(" ")
        Glide.with(context)
            .load(imageList[position]!!.url)
            .placeholder(
                R.mipmap.ic_launcher
            ).into(itemView.image_url)
        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }
}

