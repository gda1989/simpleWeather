package com.gda.simpleweather.di

import com.gda.simpleweather.interactors.WeatherInteractor
import com.gda.simpleweather.db.DbRepo
import com.gda.simpleweather.network.repos.WeatherRepo
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun getWeatherInteractor(
        weatherRepo: WeatherRepo,
        dbRepo: DbRepo
    ): WeatherInteractor = WeatherInteractor( weatherRepo, dbRepo)

}