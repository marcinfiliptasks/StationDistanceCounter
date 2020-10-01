package com.mf.data.dataSources.stationsDataSources

import com.mf.data.api.retrofit.retrofitServices.StationsRetrofitService
import com.mf.domain.dataSources.IStationsRemoteDataSource
import com.mf.domain.models.station.IStation
import com.mf.domain.models.stationKeyword.IStationKeyword
import javax.inject.Inject

class StationsRemoteDataSource @Inject constructor(
    private val apiService: StationsRetrofitService
) : IStationsRemoteDataSource {
    override suspend fun getStationKeywords(): List<IStationKeyword> = apiService.getKeywords()
    override suspend fun getStations(): List<IStation> = apiService.getStations()
}