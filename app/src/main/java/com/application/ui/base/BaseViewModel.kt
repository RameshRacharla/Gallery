package com.application.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.R
import com.application.utils.common.Resource
import com.application.utils.network.NetworkHelper
import com.application.utils.rx.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection


abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected var compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper,
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()
    val logOut: MutableLiveData<Resource<Boolean>> = MutableLiveData()


    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    protected fun responseFailureError(error: String) {
        messageString.postValue(Resource.error(error))
    }

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    -1 -> messageStringId.postValue(Resource.error(R.string.network_default_error))
                    0 -> messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        forcedLogoutUser()
//                        messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Resource.error(R.string.network_internal_error))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Resource.error(R.string.network_server_not_available))
                    HttpsURLConnection.HTTP_MOVED_TEMP ->
                        messageStringId.postValue(Resource.error(R.string.network_invalidation_error))
                    else -> messageString.postValue(Resource.error(message))
                }
            }
        }

    protected open fun forcedLogoutUser() {
        // do something
        logOut.postValue(Resource.success(true))
    }

    abstract fun onCreate()
}