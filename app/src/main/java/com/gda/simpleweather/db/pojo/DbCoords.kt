package com.gda.simpleweather.db.pojo

import io.realm.RealmObject

open class DbCoords(
    var lon: Double? = null,
    var lat: Double? = null
) : RealmObject()