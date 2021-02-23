package com.gda.simpleweather.di

import com.gda.simpleweather.network.repos.DadataRepo
import com.gda.simpleweather.network.repos.WeatherRepo
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun getWeatherRepo() = WeatherRepo()

    @Provides
    fun getDadataRepo() = DadataRepo()

}