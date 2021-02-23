package com.gda.simpleweather.network.pojo.response.weather

import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(

	@field:SerializedName("visibility")
	val visibility: Int? = null,
	@field:SerializedName("timezone")
	val timezone: Int? = null,
	@field:SerializedName("main")
	val main: MainWeatherInfo? = null,
	@field:SerializedName("clouds")
	val clouds: Clouds? = null,
	@field:SerializedName("sys")
	val sys: Sys? = null,
	@field:SerializedName("dt")
	val dt: Int? = null,
	@field:SerializedName("coord")
	val coord: Coord? = null,
	@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("cod")
	val cod: Int? = null,
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("base")
	val base: String? = null,
	@field:SerializedName("wind")
	val wind: Wind? = null
)

data class Sys(

	@field:SerializedName("country")
	val country: String? = null,
	@field:SerializedName("sunrise")
	val sunrise: Int? = null,
	@field:SerializedName("sunset")
	val sunset: Int? = null,
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("type")
	val type: Int? = null,
	@field:SerializedName("message")
	val message: Double? = null
)
