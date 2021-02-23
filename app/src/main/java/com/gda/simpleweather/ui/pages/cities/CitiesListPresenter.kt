package com.gda.simpleweather.ui.pages.cities

import android.annotation.SuppressLint
import android.content.Context
import com.gda.simpleweather.AppFacade
import com.gda.simpleweather.SWApp
import com.gda.simpleweather.interactors.WeatherInteractor
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.ui.activity.MainPresenter
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@SuppressLint("CheckResult")

@InjectViewState
class CitiesListPresenter : MvpPresenter<CitiesListView>() {

    @Inject
    lateinit var weatherInteractor: WeatherInteractor

    init {
        (SWApp.get() as AppFacade).getDataComponent()?.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getList()
    }

    private fun getList() {
        Single.zip(weatherInteractor.getList(),
            Single.create {
                val sp = SWApp.get().getSharedPreferences("sp", Context.MODE_PRIVATE)
                sp.getString(MainPresenter.CHOSEN_CITY_KEY, "")?.let { it1 -> it.onSuccess(it1) }
            }, { list: List<WeatherViewItem?>, s: String ->
                viewState.setList(list, s)
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {
                viewState.onError(it.message)
            })
    }

    fun setChosen(chosen: String) {
        Completable.fromCallable {
            val sp = SWApp.get().getSharedPreferences("sp", Context.MODE_PRIVATE)
            sp.edit().putString(MainPresenter.CHOSEN_CITY_KEY, chosen).apply()

        }
            .andThen {
                getList()
                it.onComplete()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showDetailsPage(chosen)
            }, {
                viewState.onError(it.message)
            })
    }

}