package com.gda.simpleweather.network.pojo.response.weather

import com.google.gson.annotations.SerializedName

data class Coord(

    @field:SerializedName("lon")
    val lon: Double? = null,
    @field:SerializedName("lat")
    val lat: Double? = null
)
