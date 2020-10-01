package com.mf.data.converters

import com.mf.data.database.entities.StationEntity
import com.mf.domain.models.station.IStation
import com.mf.domain.models.station.StationModel

fun IStation.toEntity() = StationEntity(
    id, name, nameSlug, latitude, longitude, hits, ibnr, city, region, country, localisedName
)

fun IStation.toModel() = StationModel(
    id, name, nameSlug, latitude, longitude, hits, ibnr, city, region, country, localisedName
)