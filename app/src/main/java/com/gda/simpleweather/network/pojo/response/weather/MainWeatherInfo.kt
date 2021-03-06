package com.gda.simpleweather.network.pojo.response.weather

import com.google.gson.annotations.SerializedName

data class MainWeatherInfo(
    @field:SerializedName("temp")
    val temp: Double? = null,
    @field:SerializedName("temp_min")
    val tempMin: Double? = null,
    @field:SerializedName("grnd_level")
    val grndLevel: Int? = null,
    @field:SerializedName("temp_kf")
    val tempKf: Double? = null,
    @field:SerializedName("humidity")
    val humidity: Int? = null,
    @field:SerializedName("pressure")
    val pressure: Int? = null,
    @field:SerializedName("sea_level")
    val seaLevel: Int? = null,
    @field:SerializedName("feels_like")
    val feelsLike: Double? = null,
    @field:SerializedName("temp_max")
    val tempMax: Double? = null,
    @field:SerializedName("temax")
    val temax: Double? = null
)