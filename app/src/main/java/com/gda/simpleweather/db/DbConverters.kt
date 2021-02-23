package com.gda.simpleweather.db

import com.gda.simpleweather.db.pojo.*
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.network.pojo.response.weather.*

object DbConverters {

    fun dbWeatherToViewItem(weatherDbItem: WeatherDbItem): WeatherViewItem? {
        var list = listOf<WeatherItem>()
        list = list.plus(
            WeatherItem(
                weatherDbItem.weather?.icon,
                weatherDbItem.weather?.description,
                weatherDbItem.weather?.main,
                weatherDbItem.weather?.id
            )
        )
        return weatherDbItem.cityName?.let {
            WeatherViewItem(
                it,
                weatherDbItem.visibility,
                weatherDbItem.timezone,
                MainWeatherInfo(
                    weatherDbItem.main?.temp,
                    weatherDbItem.main?.tempMin,
                    weatherDbItem.main?.grndLevel,
                    weatherDbItem.main?.tempKf,
                    weatherDbItem.main?.humidity,
                    weatherDbItem.main?.pressure,
                    weatherDbItem.main?.seaLevel,
                    weatherDbItem.main?.feelsLike,
                    weatherDbItem.main?.tempMax,
                    weatherDbItem.main?.temax
                ),
                Clouds(weatherDbItem.clouds?.all),
                Sys(
                    weatherDbItem.sys?.country,
                    weatherDbItem.sys?.sunrise,
                    weatherDbItem.sys?.sunset,
                    weatherDbItem.sys?.id,
                    weatherDbItem.sys?.type,
                    weatherDbItem.sys?.message
                ),
                weatherDbItem.dt,
                Coord(
                    weatherDbItem.coord?.lon,
                    weatherDbItem.coord?.lat
                ),
                list,
                weatherDbItem.cod,
                weatherDbItem.id,
                weatherDbItem.base,
                Wind(
                    weatherDbItem.wind?.deg,
                    weatherDbItem.wind?.speed
                )
            )
        }
    }

    fun apiWeatherToDbItem(weatherApiResponse: WeatherApiResponse): WeatherDbItem {
        val weatherItem = weatherApiResponse.weather?.get(0)
        return WeatherDbItem(
            weatherApiResponse.name,
            weatherApiResponse.visibility,
            weatherApiResponse.timezone,
            DbWeatherInfo(
                weatherApiResponse.main?.temp,
                weatherApiResponse.main?.tempMin,
                weatherApiResponse.main?.grndLevel,
                weatherApiResponse.main?.tempKf,
                weatherApiResponse.main?.humidity,
                weatherApiResponse.main?.pressure,
                weatherApiResponse.main?.seaLevel,
                weatherApiResponse.main?.feelsLike,
                weatherApiResponse.main?.tempMax,
                weatherApiResponse.main?.temax
            ),
            DbClouds(
                weatherApiResponse.clouds?.all
            ),
            DbSys(
                weatherApiResponse.sys?.country,
                weatherApiResponse.sys?.sunrise,
                weatherApiResponse.sys?.sunset,
                weatherApiResponse.sys?.id,
                weatherApiResponse.sys?.type,
                weatherApiResponse.sys?.message
            ),
            weatherApiResponse.dt,
            DbCoords(
                weatherApiResponse.coord?.lon,
                weatherApiResponse.coord?.lat
            ),
            DbWeatherItem(
                weatherItem?.icon,
                weatherItem?.description,
                weatherItem?.main,
                weatherItem?.id
            ),
            weatherApiResponse.cod,
            weatherApiResponse.id,
            weatherApiResponse.base,
            DbWind(
                weatherApiResponse.wind?.deg,
                weatherApiResponse.wind?.speed
            )
        )
    }

}