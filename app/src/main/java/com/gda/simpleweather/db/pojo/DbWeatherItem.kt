package com.gda.simpleweather.db.pojo

import io.realm.RealmObject

open class DbWeatherItem(
    var icon: String? = null,
    var description: String? = null,
    var main: String? = null,
    var id: Int? = null
) : RealmObject()