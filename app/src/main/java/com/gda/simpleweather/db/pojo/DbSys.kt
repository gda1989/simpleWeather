package com.gda.simpleweather.db.pojo

import io.realm.RealmObject

open class DbSys(
    var country: String? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null,
    var id: Int? = null,
    var type: Int? = null,
    var message: Double? = null
) : RealmObject()