package com.mf.data.database.views

import androidx.room.DatabaseView
import androidx.room.PrimaryKey
import com.mf.domain.models.stationView.IStationView

@DatabaseView(
    "SELECT stations.id, stations.name, stations.hits," +
            "stations.name, stations.longitude, stations.latitude, keywords.keyword FROM stations " +
            "INNER JOIN keywords ON stations.id = keywords.stationId ORDER BY hits DESC"
)
data class StationView(
    override val name: String,
    override val longitude: Double,
    override val latitude: Double,
    override val keyword: String
) : IStationView
