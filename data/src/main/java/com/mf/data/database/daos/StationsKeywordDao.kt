package com.mf.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mf.data.database.entities.StationKeywordEntity

@Dao
interface StationsKeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(keywords: List<StationKeywordEntity>)

    @Query("DELETE FROM keywords")
    fun deleteAll()
}