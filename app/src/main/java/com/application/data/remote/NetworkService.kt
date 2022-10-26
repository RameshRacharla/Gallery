package com.application.data.remote

import com.application.data.remote.response.Data
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import javax.inject.Singleton


@Singleton
interface NetworkService {

    @POST(Endpoints.DATA)
    fun getData(
        @Body request: RequestBody,
        @Header(Networking.CONTENT_TYPE) contentType: String = Networking.CONTENT_TYPE
    ): Single<Data>

    @Headers("Content-Type: application/json")
    @POST(Endpoints.DATA)
    fun getGalleryData(
        @Body body: RequestBody,
    ): Single<Data>
}
