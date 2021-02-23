package com.gda.simpleweather.network.pojo.response.weather

import com.google.gson.annotations.SerializedName

data class Clouds(

    @field:SerializedName("all")
    val all: Int? = null
)
