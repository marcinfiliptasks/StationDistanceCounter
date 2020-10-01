package com.mf.domain.dataSources

import com.mf.domain.models.station.IStation
import com.mf.domain.models.stationKeyword.IStationKeyword

interface IStationsRemoteDataSource {
    suspend fun getStationKeywords(): List<IStationKeyword>
    suspend fun getStations() :List<IStation>
}