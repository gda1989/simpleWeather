package com.gda.simpleweather.network

import com.gda.simpleweather.interactors.items.ForecastItem
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.network.pojo.response.weather.ApiForecastListItem
import com.gda.simpleweather.network.pojo.response.weather.WeatherApiResponse
import com.gda.simpleweather.ui.utils.Renderer

object NetworkConverters {

    fun apiWeatherToViewItem(apiWeather: WeatherApiResponse): WeatherViewItem {
        return apiWeather.name?.let {
            WeatherViewItem(
                it,
                apiWeather.visibility,
                apiWeather.timezone,
                apiWeather.main,
                apiWeather.clouds,
                apiWeather.sys,
                apiWeather.dt,
                apiWeather.coord,
                apiWeather.weather,
                apiWeather.cod,
                apiWeather.id,
                apiWeather.base,
                apiWeather.wind
            )
        } ?: WeatherViewItem(cityName = "")
    }

    fun forecastApiToViewItem(apiItem: ApiForecastListItem): ForecastItem {
        return ForecastItem(
            apiItem.weather?.get(0)?.icon,
            apiItem.dt,
            apiItem.main?.temp,
            apiItem.main?.pressure,
            Renderer.renderWind(apiItem.wind?.deg, apiItem.wind?.speed)
        )
    }

}