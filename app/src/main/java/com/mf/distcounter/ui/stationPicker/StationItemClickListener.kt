package com.mf.distcounter.ui.stationPicker

import com.mf.domain.models.stationView.StationInfoModel
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job

interface StationItemClickListener {
    fun processItemClick(item: StationInfoModel)
    fun processAdapterLoad(position: Int) : Job
}