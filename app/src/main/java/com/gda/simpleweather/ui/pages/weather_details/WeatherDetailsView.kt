package com.gda.simpleweather.ui.pages.weather_details

import com.gda.simpleweather.interactors.items.ForecastItem
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.ui.base.BaseMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface WeatherDetailsView : BaseMvpView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setWeather(info : WeatherViewItem)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setForecast(forecast : List<ForecastItem?>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setNoData()
}