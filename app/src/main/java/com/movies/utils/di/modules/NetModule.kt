package com.movies.utils.di.modules

import android.util.Log
import com.movies.application.MyApplication
import com.movies.network.ApiService
import com.movies.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(application: MyApplication) : Retrofit {

        val client = OkHttpClient.Builder()
        client.connectTimeout(5, TimeUnit.SECONDS)
        client.readTimeout(5, TimeUnit.SECONDS)
        client.interceptors().add(Interceptor { chain -> onOnIntercept(chain) })

        val cacheSize = 5 * 1024 * 1024 // 5 MiB
        val cache = Cache(application.cacheDir, cacheSize.toLong())
        client.cache(cache)

        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Throws(IOException::class)
    private fun onOnIntercept(chain: Interceptor.Chain): Response {
        try {
            Log.e("RETROFIT", "TIMEOUT ...")
            val response = chain.proceed(chain.request())
            //val content = UtilityMethods.convertResponseToString(response)
            //Log.d(TAG, lastCalledMethodName + " - " + content)
          //  return response.newBuilder().body(ResponseBody.create(response.body().contentType(), content)).build()
        } catch (exception: SocketTimeoutException) {
            exception.printStackTrace()
            //if (listener != null)
               // listener.onConnectionTimeout()
        }

        return chain.proceed(chain.request())
    }
}
