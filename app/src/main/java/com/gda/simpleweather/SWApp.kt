package com.gda.simpleweather

import android.app.Application
import com.gda.simpleweather.di.DataComponent

class SWApp : Application(), AppFacade {

    companion object {

        private var dataComponent: DataComponent? = null

        lateinit var instanse: SWApp

        fun get(): SWApp = instanse

    }

    override fun onCreate() {
        super.onCreate()
        instanse = this
        dataComponent = DataComponent.init(this)
    }

    override fun getDataComponent(): DataComponent? = dataComponent

}