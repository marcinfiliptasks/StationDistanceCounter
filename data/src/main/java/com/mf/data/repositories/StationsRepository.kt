package com.mf.data.repositories

import com.mf.domain.dataSources.IStationsLocalDataSource
import com.mf.domain.dataSources.IStationsRemoteDataSource
import com.mf.domain.models.station.IStation
import com.mf.domain.models.stationKeyword.IStationKeyword
import com.mf.domain.models.stationView.StationInfoModel
import com.mf.domain.repositories.IStationsRepository
import com.mf.domain.util.checkIfHaveToRefresh
import java.util.*
import javax.inject.Inject

class StationsRepository @Inject constructor(
    private val remoteDataSource: IStationsRemoteDataSource,
    private val localDataSource: IStationsLocalDataSource
) : IStationsRepository {
    private var lastSync: Date? = null
        get() {
            if (field == null) {
                field = localDataSource.checkLastSync()
            }
            return field
        }

    override suspend fun getStationKeywords() = remoteDataSource.getStationKeywords().cacheKeyword()

    override suspend fun checkLastSync(): Date? = lastSync

    override suspend fun setLastSync(date: Date) = localDataSource.setLastSync(date).also {
        lastSync = date
    }

    override suspend fun clearTables() {
        localDataSource.clearKeywordsTable()
        localDataSource.clearStationsTable()
    }

    override suspend fun getStations() = remoteDataSource.getStations().cacheStations()

    override suspend fun getStationsData(searchQuery: String, position: Int): List<StationInfoModel> {
        if(checkIfHaveToRefresh(lastSync)){
           processSync()
        }
        return  localDataSource.getStationsData(searchQuery, position)
    }

    private suspend fun processSync() {
        clearTables()
        getStations()
        getStationKeywords()
    }

    private suspend fun List<IStationKeyword>.cacheKeyword() =
        localDataSource.storeStationKeywords(this)

    private suspend fun List<IStation>.cacheStations() = localDataSource.storeStations(this)
}


