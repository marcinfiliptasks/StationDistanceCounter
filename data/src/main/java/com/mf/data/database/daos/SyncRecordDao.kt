package com.mf.data.database.daos

import androidx.room.*
import com.mf.data.database.entities.SyncRecordEntity

@Dao
interface SyncRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(syncRecord: SyncRecordEntity)

    @Query("SELECT * FROM SyncRecordEntity LIMIT 1")
    fun get(): SyncRecordEntity?

    @Query("DELETE FROM SyncRecordEntity")
    fun deleteAll()
}