package com.mf.data.api.responseModels

import com.fasterxml.jackson.annotation.JsonProperty
import com.mf.domain.models.station.IStation

data class StationResponseItem(
    @JsonProperty("id")
    override val id: Long,
    @JsonProperty("name")
    override val name: String,
    @JsonProperty("name_slug")
    override val nameSlug: String,
    @JsonProperty("latitude")
    override val latitude: Double,
    @JsonProperty("longitude")
    override val longitude: Double,
    @JsonProperty("hits")
    override val hits:Long,
    @JsonProperty("ibnr")
    override val ibnr: String?,
    @JsonProperty("city")
    override val city:String,
    @JsonProperty("region")
    override val region: String,
    @JsonProperty("country")
    override val country: String,
    @JsonProperty("localised_name")
    override val localisedName:String?
):IStation