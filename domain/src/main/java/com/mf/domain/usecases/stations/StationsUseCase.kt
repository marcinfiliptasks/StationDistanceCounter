package com.mf.domain.usecases.stations

import com.mf.domain.consts.STATIONS_COUNT_TO_GET
import com.mf.domain.models.stationView.StationInfoModel
import com.mf.domain.repositories.IStationsRepository
import java.util.*
import javax.inject.Inject

class StationsUseCase @Inject constructor(
    private val stationsRepository: IStationsRepository
) : IStationsUseCase {

    override suspend fun getStationKeywords() = stationsRepository.getStationKeywords()

    override suspend fun getStations() = stationsRepository.getStations()

    override suspend fun setLastSync(date: Date) = stationsRepository.setLastSync(date)

    override suspend fun clearTables() = stationsRepository.clearTables()

    override suspend fun getStationsData(
        searchQuery: String,
        position: Int
    ): List<StationInfoModel> =
        stationsRepository.getStationsData(searchQuery, position + STATIONS_COUNT_TO_GET)

    override suspend fun checkLastSync(): Date? = stationsRepository.checkLastSync()
}

