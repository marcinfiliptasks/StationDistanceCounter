package com.mf.distcounter.framework.location

import android.location.Location
import com.mf.domain.models.stationView.StationInfoModel
import com.mf.domain.usecases.distanceCalculator.IDistanceCalculatorUseCase
import javax.inject.Inject

class DistanceCalculator @Inject constructor(): IDistanceCalculatorUseCase {
    private fun StationInfoModel.toLocation() = Location("API").apply {
        latitude = this@toLocation.latitude
        longitude = this@toLocation.longitude
    }

    override suspend fun calculateDistance(
        pointFrom: StationInfoModel,
        pointTo: StationInfoModel
    ): Float = pointFrom.toLocation().distanceTo(pointTo.toLocation()) / 1000
}