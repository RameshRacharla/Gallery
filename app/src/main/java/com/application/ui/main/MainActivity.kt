package com.application.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.R
import com.application.data.remote.response.Data
import com.application.databinding.ActivityMainBinding
import com.application.di.component.ActivityComponent
import com.application.ui.base.BaseActivity
import com.application.utils.common.CommonClass
import com.application.utils.common.Constants
import com.application.utils.common.Toaster
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray


class MainActivity : BaseActivity<MainViewModel>() {
    private lateinit var reportManager: RecyclerView.LayoutManager
    private lateinit var mainAdapter: MainAdapter

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun provideLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        setupRecycler()
        Toaster.show(this, "Success"+Constants.galleryData.size)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.loading.observe(this, Observer {
            if (it)
                if (Constants.galleryData.isNotEmpty()) {
                    mainAdapter.onAddItems(Constants.galleryData)
                    mainAdapter.notifyDataSetChanged()
                }

        })
    }

    private fun setupRecycler() {
        mainAdapter =
            MainAdapter(
                Constants.galleryData,
                this
            )
        if (recyclerview_images != null) {
            recyclerview_images.apply {
                reportManager = GridLayoutManager(context, 3)
                layoutManager = reportManager
                adapter = mainAdapter

            }
        }

    }
}