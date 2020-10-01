package com.mf.domain.dataSources

import com.mf.domain.models.station.IStation
import com.mf.domain.models.stationKeyword.IStationKeyword
import com.mf.domain.models.stationView.StationInfoModel
import java.util.*

interface IStationsLocalDataSource {
    suspend fun storeStationKeywords(keywords: List<IStationKeyword>)
    suspend fun storeStations(stations: List<IStation>)
    fun getStationsData(searchQuery: String, itemCount: Int): List<StationInfoModel>
    fun checkLastSync(): Date?
    suspend fun setLastSync(date: Date)
    suspend fun clearStationsTable()
    suspend fun clearKeywordsTable()
}