package com.mf.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mf.domain.models.station.IStation

@Entity(tableName = "stations")
data class StationEntity (
    @PrimaryKey override val id: Long,
    override val name: String,
    override val nameSlug: String,
    override val latitude: Double,
    override val longitude: Double,
    override val hits: Long,
    override val ibnr: String?,
    override val city: String,
    override val region: String,
    override val country: String,
    override val localisedName: String?
): IStation