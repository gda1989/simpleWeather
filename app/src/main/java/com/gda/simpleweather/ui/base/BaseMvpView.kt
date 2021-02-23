package com.gda.simpleweather.ui.base

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseMvpView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onError(error: String?)

}