package com.application.utils.common

import android.content.Context
import com.application.ui.main.MainActivity
import com.application.ui.splash.SplashActivity

object CommonClass {

    fun loadJSONFromAssets(main: SplashActivity, fileName: String): String? {
        return main.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }
}