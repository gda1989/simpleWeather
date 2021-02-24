package com.gda.simpleweather.ui.pages.weather_details

import android.annotation.SuppressLint
import com.gda.simpleweather.AppFacade
import com.gda.simpleweather.SWApp
import com.gda.simpleweather.interactors.WeatherInteractor
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@SuppressLint("CheckResult")
@InjectViewState
class WeatherDetailsPresenter : MvpPresenter<WeatherDetailsView>() {

    @Inject
    lateinit var weatherInteractor: WeatherInteractor

    init {
        (SWApp.get() as AppFacade).getDataComponent()?.inject(this)
    }

    fun getWeather(name: String) {
        getForecast(name)
        weatherInteractor.getWeather(name, true)
            ?.subscribe({
                it?.let { viewState.setWeather(it) } ?: viewState.setNoData()
            }, {
                viewState.onError(it.message)
            })
    }

    private fun getForecast(name: String) {
        weatherInteractor.getForecast(name)
            ?.subscribe({
                it?.let { it1 -> viewState.setForecast(it1) }
            }, {
                viewState.onError(it.message)
            })
    }

}