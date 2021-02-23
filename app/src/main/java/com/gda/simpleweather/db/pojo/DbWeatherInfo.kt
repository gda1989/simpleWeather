package com.gda.simpleweather.db.pojo

import io.realm.RealmObject

open class DbWeatherInfo(

    var temp: Double? = null,
    var tempMin: Double? = null,
    var grndLevel: Int? = null,
    var tempKf: Double? = null,
    var humidity: Int? = null,
    var pressure: Int? = null,
    var seaLevel: Int? = null,
    var feelsLike: Double? = null,
    var tempMax: Double? = null,
    var temax: Double? = null

) : RealmObject()