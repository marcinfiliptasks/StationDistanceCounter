package com.mf.data.converters

import com.mf.data.database.entities.StationKeywordEntity
import com.mf.domain.models.stationKeyword.IStationKeyword

fun IStationKeyword.toEntity() = StationKeywordEntity(
    id, keyword, stationId
)