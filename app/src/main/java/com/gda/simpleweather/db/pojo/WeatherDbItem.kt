package com.gda.simpleweather.db.pojo

import com.gda.simpleweather.network.pojo.response.weather.*
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class WeatherDbItem(

    @PrimaryKey
    var cityName: String? = null,
    var visibility: Int? = null,
    var timezone: Int? = null,
    var main: DbWeatherInfo? = null,
    var clouds: DbClouds? = null,
    var sys: DbSys? = null,
    var dt: Int? = null,
    var coord: DbCoords? = null,
    var weather: DbWeatherItem? = null,
    var cod: Int? = null,
    var id: Int? = null,
    var base: String? = null,
    var wind: DbWind? = null

) : RealmObject()