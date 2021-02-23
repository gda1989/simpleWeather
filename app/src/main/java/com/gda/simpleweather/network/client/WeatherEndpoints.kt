package com.gda.simpleweather.network.client

import com.gda.simpleweather.network.pojo.response.weather.ForecastResponse
import com.gda.simpleweather.network.pojo.response.weather.WeatherApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherEndpoints {

    @GET("/data/2.5/weather?")
    fun loadWeather(
        @Query("q") cityName: String?,
        @Query("APPID") code: String?,
        @Query("lang") lang : String
    ): Single<WeatherApiResponse?>?

    @GET("/data/2.5/forecast?")
    fun loadForecast(
        @Query("q") cityName: String?,
        @Query("APPID") code: String?,
        @Query("lang") lang : String
    ): Single<ForecastResponse?>?



}