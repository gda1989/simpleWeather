package com.gda.simpleweather.ui.pages.cities

import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.network.pojo.response.weather.WeatherApiResponse
import com.gda.simpleweather.ui.base.BaseMvpView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CitiesListView : BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setList(list : List<WeatherViewItem?>, chosen : String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showDetailsPage(chosen : String)

}