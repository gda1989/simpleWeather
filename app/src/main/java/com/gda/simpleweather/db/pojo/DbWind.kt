package com.gda.simpleweather.db.pojo

import io.realm.RealmObject

open class DbWind(
    var deg: Int? = null,
    var speed: Double? = null
) : RealmObject()