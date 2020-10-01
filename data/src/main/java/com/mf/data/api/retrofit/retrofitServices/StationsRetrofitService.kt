package com.mf.data.api.retrofit.retrofitServices

import com.mf.data.api.responseModels.KeywordResponseItem
import com.mf.data.api.responseModels.StationResponseItem
import com.mf.domain.consts.STATIONS_HEADER
import retrofit2.http.GET
import retrofit2.http.Headers


interface StationsRetrofitService {
    @Headers(STATIONS_HEADER)
    @GET("v2/main/station_keywords")
    suspend fun getKeywords():List<KeywordResponseItem>

    @Headers(STATIONS_HEADER)
    @GET("v2/main/stations")
    suspend fun getStations():List<StationResponseItem>
}