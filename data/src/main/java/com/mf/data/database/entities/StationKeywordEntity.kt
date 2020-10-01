package com.mf.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mf.domain.models.stationKeyword.IStationKeyword

@Entity(tableName = "keywords")
data class StationKeywordEntity (
    @PrimaryKey
    override val id: Long,
    override var keyword: String,
    override val stationId: Long
): IStationKeyword