package com.mf.domain.models.stationView

import java.io.Serializable

data class StationInfoModel(
    override val name: String,
    override val keyword: String,
    override val longitude: Double,
    override val latitude: Double
) : Serializable, IStationView