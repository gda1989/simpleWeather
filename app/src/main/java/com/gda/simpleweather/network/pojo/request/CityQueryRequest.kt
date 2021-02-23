package com.gda.simpleweather.network.pojo.request

data class CityQueryRequest(
    val query : String,
    val locations: List<Location> = listOf(Location())
)

data class Location(
    val city_type_full: String = "город"
)
