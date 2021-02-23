package com.gda.simpleweather

import com.gda.simpleweather.di.DataComponent

interface AppFacade {

    fun getDataComponent() : DataComponent?

}