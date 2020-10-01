package com.mf.domain.models.stationKeyword

data class StationKeywordModel(
    override val id: Long,
    override var keyword: String,
    override val stationId: Long
) : IStationKeyword {
    override fun toString(): String = keyword
}