package com.gda.simpleweather.interactors.items

data class ForecastItem(
    val iconCode: String? = null,
    val time: Long? = null,
    val temp : Double? = null,
    val pressure : Int? = null,
    val wind : String? = null
)