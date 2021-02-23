package com.gda.simpleweather.interactors

import com.gda.simpleweather.db.DbRepo
import com.gda.simpleweather.interactors.items.ForecastItem
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.network.NetworkConverters
import com.gda.simpleweather.network.repos.WeatherRepo
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherInteractor(
    private val weatherRepo: WeatherRepo,
    private val dbRepo: DbRepo
) {

    fun initSomeDefaultList(): Completable {
        return Completable.fromSingle(Single.concat(
            getList(),
            Single.zip(
                getWeather("Москва"),
                getWeather("Томск"),
                getWeather("Новосибирск"),
                getWeather("Владивосток"),
                { weatherViewItem: WeatherViewItem, weatherViewItem1: WeatherViewItem, weatherViewItem2: WeatherViewItem, weatherViewItem3: WeatherViewItem ->
                    listOf(
                        weatherViewItem,
                        weatherViewItem1,
                        weatherViewItem2,
                        weatherViewItem3
                    )
                })
        )
            .filter {           //отфильтровываем заглушку-пустой лист от дбРипы
                it.isNotEmpty() }
            .first(
                listOf()))

    }

    fun getWeather(q: String): Single<WeatherViewItem?> {
        return Single.concat(
            dbRepo.read(q),
            weatherRepo.getWeather(q)
                ?.flatMap { it ->
                    dbRepo
                        .save(it)
                        .andThen(Single.just(it))
                }
                ?.map {
                    NetworkConverters.apiWeatherToViewItem(it)
                })
            .filter { !it.cityName.isNullOrEmpty() }
            .first(WeatherViewItem(""))
            .map {
                it.cityName
                return@map it
            }
    }

    fun getForecast(q: String): Single<List<ForecastItem?>?>? {
        return weatherRepo.getForecast(q)?.map { it ->
            return@map it.list?.map { apiItem ->
                apiItem?.let { it1 ->
                    NetworkConverters.forecastApiToViewItem(
                        it1
                    )
                }
            }
        }
            ?.subscribeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getList(): Single<List<WeatherViewItem?>> {
        return dbRepo.readAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}