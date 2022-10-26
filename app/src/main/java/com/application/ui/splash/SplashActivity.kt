package com.application.ui.splash

import android.os.Bundle
import com.application.databinding.ActivitySplashBinding
import com.application.di.component.ActivityComponent
import com.application.ui.base.BaseActivity

import android.content.Intent
import android.os.Handler
import com.application.R
import com.application.data.remote.response.Data
import com.application.ui.main.MainActivity
import com.application.utils.common.CommonClass
import com.application.utils.common.Constants
import com.application.utils.common.Toaster
import org.json.JSONArray


class SplashActivity : BaseActivity<SplashViewModel>() {

    private val displayLength: Long = 3000
    private lateinit var binding: ActivitySplashBinding
    lateinit var dataModelList: ArrayList<Data>
    override fun provideLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        bindJSONDataInFacilityList()

        Handler(mainLooper).postDelayed(Runnable { /* Intent that will start - MainActivity. */
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            //finish()
        }, displayLength)
    }

    fun bindJSONDataInFacilityList() {
        dataModelList = ArrayList<Data>()
        val dataJsonArray = JSONArray(
            CommonClass.loadJSONFromAssets(
                this,
                "data.json"
            )
        ) // Extension Function call here
        for (i in 0 until dataJsonArray.length()) {
            var dataModel = Data()
            var dataJsonObject = dataJsonArray.getJSONObject(i)
            if (dataJsonObject.has("copyright")) {
                dataModel.copyright = dataJsonObject.getString("copyright")
            } else {
                dataModel.copyright = ""
            }

            dataModel.date = dataJsonObject.getString("date")
            dataModel.explanation = dataJsonObject.getString("explanation")
            dataModel.hdurl = dataJsonObject.getString("hdurl")
            dataModel.media_type = dataJsonObject.getString("media_type")
            dataModel.service_version = dataJsonObject.getString("service_version")
            dataModel.title = dataJsonObject.getString("title")
            dataModel.url = dataJsonObject.getString("url")

            dataModelList.add(dataModel)
            Constants.galleryData = dataModelList
        }

    }
}