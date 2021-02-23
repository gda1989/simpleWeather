package com.gda.simpleweather.network.repos

import com.gda.simpleweather.network.client.ApiClient
import com.google.gson.JsonObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DadataRepo {

    fun search(q: String): Single<List<String>> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("query", q)
        val bound = JsonObject()
        bound.addProperty("value", "city")

        //согласно документации, баунды должны ограничить информацию в результатах выдачи, чтобы приходили только названия городов. Не работает, разбираться некогда
        jsonObject.add("from_bound", bound)
        jsonObject.add("to_bound", bound)
        return ApiClient.getDDATAInstance().search(jsonObject)
            .map { it ->
                val arrayList = ArrayList<String>()
                it.suggestions.forEach { searchResult ->
                    val parts = searchResult.value.split(",")
                    parts.forEach{
                        if(it.trim().startsWith("г "))
                            arrayList.add(it.replace("г ", "").trim())
                    }
                }
                return@map arrayList.toList()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}