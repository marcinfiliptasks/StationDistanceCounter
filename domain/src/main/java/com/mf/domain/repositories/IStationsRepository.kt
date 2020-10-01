package com.mf.domain.repositories

import com.mf.domain.models.stationView.StationInfoModel
import java.util.*

interface IStationsRepository {
    suspend fun getStationKeywords()
    suspend fun getStations()
    suspend fun getStationsData(searchQuery: String, position: Int): List<StationInfoModel>
    suspend fun checkLastSync(): Date?
    suspend fun setLastSync(date: Date)
    suspend fun clearTables()
}