package com.application

import android.app.Application
import com.application.di.component.ApplicationComponent
import com.application.di.component.DaggerApplicationComponent
import com.application.di.module.ApplicationModule


class AppController : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}