package com.gda.simpleweather.ui.activity

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import com.gda.simpleweather.AppFacade
import com.gda.simpleweather.SWApp
import com.gda.simpleweather.interactors.WeatherInteractor
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    companion object {
        const val CHOSEN_CITY_KEY = "chosen_city_key"
    }

    init {
        (SWApp.get() as AppFacade).getDataComponent()?.inject(this)
    }

    @Inject
    lateinit var weatherInteractor: WeatherInteractor

    @SuppressLint("CheckResult")

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        weatherInteractor.initSomeDefaultList().doOnComplete {
            Single.create<String?> {
                val sp = SWApp.get().getSharedPreferences("sp", MODE_PRIVATE)
                sp.getString(CHOSEN_CITY_KEY, "")?.let { it1 -> it.onSuccess(it1) } ?: ""
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.setupNavigation(it)
                }, {
                    viewState.setupNavigation(null)
                })
        }.doOnError {

        }
            .subscribe()
    }

}