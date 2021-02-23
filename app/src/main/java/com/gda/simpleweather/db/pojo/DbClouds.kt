package com.gda.simpleweather.db.pojo

import io.realm.RealmObject

open class DbClouds(
    var all: Int? = null
) : RealmObject()