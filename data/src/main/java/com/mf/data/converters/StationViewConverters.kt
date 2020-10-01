package com.mf.data.converters

import com.mf.domain.models.stationView.IStationView
import com.mf.domain.models.stationView.StationInfoModel

fun IStationView.toModel() = StationInfoModel(name, keyword, longitude, latitude)