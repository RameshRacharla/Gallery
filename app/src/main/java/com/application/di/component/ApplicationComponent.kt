package com.application.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.application.data.remote.NetworkService
import com.application.data.repository.UserRepository
import com.application.di.ApplicationContext
import com.application.di.module.ApplicationModule
import com.application.utils.network.NetworkHelper
import com.application.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: Application)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getSharedPreferences(): SharedPreferences

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getNetworkHelper(): NetworkHelper

    fun getUserRepository(): UserRepository

}