package com.movies.network

import com.movies.model.Model
import com.movies.utils.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @GET("top")
    fun getTopMovies(@Query("token") token : String) : Observable<Model.Response>

    companion object {

        fun create(): ApiInterface {

            val client = OkHttpClient.Builder()
            client.connectTimeout(20, TimeUnit.SECONDS)
            client.readTimeout(20, TimeUnit.SECONDS)
            client.build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}