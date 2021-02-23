package com.gda.simpleweather.di

import android.app.Application
import com.gda.simpleweather.ui.activity.MainPresenter
import com.gda.simpleweather.ui.pages.cities.CitiesListPresenter
import com.gda.simpleweather.ui.pages.weather_details.WeatherDetailsPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DbModule::class, NetworkModule::class, DataModule::class])
interface DataComponent {

    companion object {
        fun init(application: Application): DataComponent =
            DaggerDataComponent.builder()
                .application(application)
                .build()
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): DataComponent
    }

    fun inject(mainPresenter: MainPresenter)

    fun inject(citiesListPresenter: CitiesListPresenter)

    fun inject(weatherDetailsPresenter: WeatherDetailsPresenter)
}