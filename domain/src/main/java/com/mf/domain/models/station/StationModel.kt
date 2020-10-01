package com.mf.domain.models.station

data class StationModel(
    override val id: Long,
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