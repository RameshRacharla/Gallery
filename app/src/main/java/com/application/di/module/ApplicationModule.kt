package com.application.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.application.BuildConfig
import com.application.data.remote.NetworkService
import com.application.data.remote.Networking
import com.application.di.ApplicationContext
import com.application.utils.network.NetworkHelper
import com.application.utils.rx.RxSchedulerProvider
import com.application.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()


    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("yogifi_pref", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.createAPI(
           BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)


}