package com.mf.distcounter.ui.stationPicker

import com.mf.domain.models.stationView.StationInfoModel
import java.io.Serializable

data class StationPickerUiState(
    val firstStation: StationInfoModel? = null,
    val secondStation: StationInfoModel? = null
) : Serializable

fun StationPickerUiState?.getValue() =
    this?.let { this } ?: StationPickerUiState()

