package com.gda.simpleweather.network.client

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.gda.simpleweather.BuildConfig
import com.gda.simpleweather.SWApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private fun getClient(authorized : Boolean) : OkHttpClient{

        val builder = OkHttpClient.Builder()

        if(authorized){
            builder.addInterceptor {
                return@addInterceptor it.proceed(
                    it.request().newBuilder()
                        .header("Authorization", "Token ${BuildConfig.dadaak}")
                        .build()
                )
            }
        }

        builder
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(80, TimeUnit.SECONDS)
            .callTimeout(90, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
                .addInterceptor(ChuckerInterceptor(context = SWApp.get()))
        }

        return builder.build()
    }

    fun <S> createService(
        serviceClass: Class<S>,
        baseAddress: String
    ): S {
        return Retrofit.Builder()
            .baseUrl(baseAddress)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient(false))
            .build()
            .create(serviceClass)
    }

    fun <S> createAuthorizedService(
        serviceClass: Class<S>,
        baseAddress: String
    ): S {
        return Retrofit.Builder()
            .baseUrl(baseAddress)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient(true))
            .build()
            .create(serviceClass)
    }

}