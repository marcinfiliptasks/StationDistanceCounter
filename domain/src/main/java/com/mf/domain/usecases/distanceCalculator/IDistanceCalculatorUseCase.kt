package com.mf.domain.usecases.distanceCalculator

import com.mf.domain.models.stationView.StationInfoModel

interface IDistanceCalculatorUseCase {
    suspend fun calculateDistance(pointFrom: StationInfoModel, pointTo: StationInfoModel): Float
}