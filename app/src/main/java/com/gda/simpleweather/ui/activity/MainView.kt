package com.gda.simpleweather.ui.activity

import com.gda.simpleweather.ui.base.BaseMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainView : BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setupNavigation(defaultCity : String?)

}