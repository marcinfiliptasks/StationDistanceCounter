package com.mf.distcounter.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mf.distcounter.ui.stationPicker.StationPickerUiState
import com.mf.domain.models.stationView.StationInfoModel
import com.mf.domain.usecases.distanceCalculator.IDistanceCalculatorUseCase
import com.mf.domain.util.toKmString

class MainViewModel @ViewModelInject constructor(
    private val distanceCalculatorUseCase: IDistanceCalculatorUseCase
) : ViewModel() {

    private var _stations: MutableLiveData<Pair<StationInfoModel?, StationInfoModel?>> =
        MutableLiveData()
    var stations: LiveData<Pair<StationInfoModel?, StationInfoModel?>> = _stations

    fun setStations(stations: StationPickerUiState) {
        _stations.postValue(stations.firstStation to stations.secondStation)
    }

    val geoData: LiveData<String> = stations.switchMap {
        it.let { locations ->
            liveData {
                emit(
                    distanceCalculatorUseCase.calculateDistance(
                        requireNotNull(locations.first),
                        requireNotNull(locations.second)
                    ).toKmString()
                )
            }
        }
    }
}


