package com.gda.simpleweather.di

import com.gda.simpleweather.network.WeatherRepo
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun getWeatherRepo() = WeatherRepo()

}