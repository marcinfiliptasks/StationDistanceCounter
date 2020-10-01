package com.mf.data.dataSources.stationsDataSources

import com.mf.data.converters.toEntity
import com.mf.data.converters.toModel
import com.mf.data.database.daos.StationsDao
import com.mf.data.database.daos.StationsKeywordDao
import com.mf.data.database.daos.StationsViewDao
import com.mf.data.database.daos.SyncRecordDao
import com.mf.data.database.entities.SyncRecordEntity
import com.mf.domain.dataSources.IStationsLocalDataSource
import com.mf.domain.models.station.IStation
import com.mf.domain.models.stationKeyword.IStationKeyword
import com.mf.domain.models.stationView.StationInfoModel
import java.util.*
import javax.inject.Inject

class StationsLocalDataSource @Inject constructor(
    private val stationsKeywordDao: StationsKeywordDao,
    private val stationsDao: StationsDao,
    private val stationsViewDao: StationsViewDao,
    private val syncRecordDao: SyncRecordDao
) : IStationsLocalDataSource {

    override suspend fun storeStationKeywords(keywords: List<IStationKeyword>) =
        stationsKeywordDao.insertAll(keywords.map { it.toEntity() })

    override suspend fun storeStations(stations: List<IStation>) =
        stationsDao.insertAll(stations.map { it.toEntity() })

    override fun getStationsData(searchQuery:String, itemCount: Int): List<StationInfoModel> =
        stationsViewDao.getStationsData(itemCount, searchQuery).map { it.toModel() }

    override fun checkLastSync(): Date? = syncRecordDao.get()?.date

    override suspend fun setLastSync(date: Date) =
        syncRecordDao.insert(SyncRecordEntity(date = date))

    override suspend fun clearStationsTable() = stationsDao.deleteAll()

    override suspend fun clearKeywordsTable() = stationsKeywordDao.deleteAll()
}