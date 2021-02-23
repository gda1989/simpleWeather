package com.gda.simpleweather.db

import com.gda.simpleweather.SWApp
import com.gda.simpleweather.db.pojo.WeatherDbItem
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.network.pojo.response.weather.WeatherApiResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Case
import io.realm.Realm

class DbRepo {

    init {
        Realm.init(SWApp.get())
    }

    fun readAll(): Single<List<WeatherViewItem?>> {
        return Single.create<List<WeatherViewItem?>> { emitter ->
            val realm = Realm.getDefaultInstance()
            val result = realm.where(WeatherDbItem::class.java)
                .findAll()
            result?.let { results ->
                emitter.onSuccess(results.map { DbConverters.dbWeatherToViewItem(it) })
            } ?: emitter.onSuccess(listOf())
            realm.close()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun read(cityName: String):
            Single<WeatherViewItem?> {
        return Single.create<WeatherViewItem?> { emitter ->
            val realm = Realm.getDefaultInstance()
            val result = realm.where(WeatherDbItem::class.java)
                .equalTo("cityName", cityName, Case.INSENSITIVE)
                .findFirst()
            result?.let {
                DbConverters.dbWeatherToViewItem(it)?.let { it1 -> emitter.onSuccess(it1) }
            } ?: DbConverters.dbWeatherToViewItem(WeatherDbItem(""))?.let { emitter.onSuccess(it) }
            realm.close()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun save(item: WeatherApiResponse): Completable {
        return Completable.fromAction {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.insertOrUpdate(DbConverters.apiWeatherToDbItem(item))
            realm.commitTransaction()
            realm.close()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(cityName: String): Completable {
        return Completable.fromAction {
            val realm = Realm.getDefaultInstance()
            val result = realm.where(WeatherDbItem::class.java)
                .equalTo("cityName", cityName, Case.INSENSITIVE)
                .findFirst()
            result?.let { weatherDbItem ->
                realm.beginTransaction()
                weatherDbItem.deleteFromRealm()
                realm.commitTransaction()
            }
            realm.close()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}