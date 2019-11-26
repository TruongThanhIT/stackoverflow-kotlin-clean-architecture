package com.thanht.stackoverflow.data.net


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.thanht.stackoverflow.data.BuildConfig
import java.util.concurrent.TimeUnit

private const val OK_HTTP_TIMEOUT = 15
private const val BASE_URL = "https://api.stackexchange.com/"

class ApiConnection {
    val retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()
                        .setLevel(if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY
                        else
                            HttpLoggingInterceptor.Level.NONE))
                .retryOnConnectionFailure(true)
                .followRedirects(true)
                .connectTimeout(OK_HTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(OK_HTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(OK_HTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
