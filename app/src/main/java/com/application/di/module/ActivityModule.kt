package com.application.di.module

import androidx.lifecycle.ViewModelProvider
import com.application.data.repository.UserRepository
import com.application.ui.base.BaseActivity
import com.application.ui.gallery.GalleryViewModel
import com.application.ui.main.MainViewModel
import com.application.ui.splash.SplashViewModel
import com.application.utils.common.ViewModelProviderFactory
import com.application.utils.network.NetworkHelper
import com.application.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SplashViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
        }).get(SplashViewModel::class.java)

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): MainViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
        }).get(MainViewModel::class.java)

    @Provides
    fun provideGalleryViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): GalleryViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(GalleryViewModel::class) {
            GalleryViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
        }).get(GalleryViewModel::class.java)
}