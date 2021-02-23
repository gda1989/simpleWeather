package com.gda.simpleweather.network.client

import com.gda.simpleweather.BuildConfig

class ApiClient {

    companion object {

        fun getWeatherInstance() = ServiceGenerator.createService(
            WeatherEndpoints::class.java, BuildConfig.baseWeatherAddress
        )

        fun getDDATAInstance() = ServiceGenerator.createAuthorizedService(
            DADATAEndoitns::class.java, BuildConfig.baseDADATAAddress
        )
    }

}