package com.mf.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mf.data.database.entities.StationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<StationEntity>)

    @Query("DELETE FROM stations")
    fun deleteAll()
}