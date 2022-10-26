package com.application.ui.gallery

import androidx.lifecycle.MutableLiveData
import com.application.data.repository.UserRepository
import com.application.ui.base.BaseViewModel
import com.application.utils.network.NetworkHelper
import com.application.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class GalleryViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    override fun onCreate() {
        loading.postValue(true)
    }
}