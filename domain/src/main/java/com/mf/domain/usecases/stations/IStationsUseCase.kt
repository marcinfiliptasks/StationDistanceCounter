package com.mf.domain.usecases.stations

import com.mf.domain.models.stationView.StationInfoModel
import java.util.*

interface IStationsUseCase {
    suspend fun getStationKeywords()
    suspend fun getStationsData(searchQuery: String, position: Int): List<StationInfoModel>
    suspend fun checkLastSync(): Date?
    suspend fun getStations()
    suspend fun setLastSync(date: Date)
    suspend fun clearTables()
}