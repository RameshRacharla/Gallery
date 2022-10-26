package com.application.ui.splash

import com.application.data.repository.UserRepository
import com.application.ui.base.BaseViewModel
import com.application.utils.network.NetworkHelper
import com.application.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {

    }
}