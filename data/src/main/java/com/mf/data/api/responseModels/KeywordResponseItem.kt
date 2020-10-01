package com.mf.data.api.responseModels

import com.fasterxml.jackson.annotation.JsonProperty
import com.mf.domain.models.stationKeyword.IStationKeyword


data class KeywordResponseItem(
    @JsonProperty("id")
    override val id: Long,
    @JsonProperty("keyword")
    override var keyword: String,
    @JsonProperty("station_id")
    override val stationId: Long
) : IStationKeyword