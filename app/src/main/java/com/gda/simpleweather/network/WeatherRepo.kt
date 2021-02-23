package com.gda.simpleweather.network

import com.gda.simpleweather.BuildConfig
import com.gda.simpleweather.network.client.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherRepo() {

    fun getWeather(q: String) = ApiClient.getWeatherInstance()
        .loadWeather(q, BuildConfig.wak, "RU")
        ?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())

    fun getForecast(q: String) = ApiClient.getWeatherInstance()
        .loadForecast(q, BuildConfig.wak, "RU")
        ?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())

}