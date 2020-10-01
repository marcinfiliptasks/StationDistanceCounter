package com.mf.distcounter.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mf.data.database.daos.StationsDao
import com.mf.data.database.daos.StationsKeywordDao
import com.mf.data.database.daos.StationsViewDao
import com.mf.data.database.daos.SyncRecordDao
import com.mf.data.database.entities.StationEntity
import com.mf.data.database.entities.StationKeywordEntity
import com.mf.data.database.entities.SyncRecordEntity
import com.mf.data.database.typeConverters.DateConverter
import com.mf.data.database.views.StationView

@Database(
    version = 1,
    entities = [StationKeywordEntity::class, StationEntity::class, SyncRecordEntity::class],
    views = [StationView::class]
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationKeywordsDao(): StationsKeywordDao
    abstract fun stationDao(): StationsDao
    abstract fun stationsViewDao(): StationsViewDao
    abstract fun syncRecordDao(): SyncRecordDao
}