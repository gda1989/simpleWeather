package com.gda.simpleweather.interactors.items

import com.gda.simpleweather.network.pojo.response.weather.*

data class WeatherViewItem(
    val cityName: String? = null,
    val visibility: Int? = null,
    val timezone: Int? = null,
    val main: MainWeatherInfo? = null,
    val clouds: Clouds? = null,
    val sys: Sys? = null,
    val dt: Int? = null,
    val coord: Coord? = null,
    val weather: List<WeatherItem?>? = null,
    val cod: Int? = null,
    val id: Int? = null,
    val base: String? = null,
    val wind: Wind? = null
)
