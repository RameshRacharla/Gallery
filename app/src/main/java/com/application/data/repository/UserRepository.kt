package com.application.data.repository

import com.application.data.localprefs.UserPreferences
import com.application.data.remote.NetworkService
import com.application.data.remote.response.Data
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val userPreferences: UserPreferences
) {

    fun getData(jsonObject: RequestBody): Single<Data> {
        return networkService.getData(jsonObject)
    }

    fun getUserName(): String? =
        userPreferences.getUserName()

    fun setUserName(userName: String) =
        userPreferences.setUserName(userName)
}