package com.application.ui.gallery

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.application.R
import com.application.di.component.ActivityComponent
import com.application.ui.base.BaseActivity
import com.application.utils.common.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_viewpager.*


class GalleryViewActivity : BaseActivity<GalleryViewModel>() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun provideLayoutId(): Int {
        return R.layout.activity_viewpager
    }

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        viewPagerAdapter = ViewPagerAdapter(this, Constants.galleryData)
        viewPager.adapter = viewPagerAdapter
        viewPager.setCurrentItem(Constants.position)
    }

}