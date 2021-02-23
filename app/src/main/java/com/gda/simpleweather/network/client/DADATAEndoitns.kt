package com.gda.simpleweather.network.client

import com.gda.simpleweather.network.pojo.response.search.SearchResultResponse
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface DADATAEndoitns {

    @POST("/suggestions/api/4_1/rs/suggest/address")
    fun search(
        @Body request: JsonObject
    ): Single<SearchResultResponse>

}