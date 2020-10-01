package com.mf.distcounter.ui.stationPicker

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mf.domain.models.stationView.StationInfoModel
import com.mf.domain.usecases.stations.IStationsUseCase
import com.mf.domain.util.exceptionHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class StationPickerViewModel @ViewModelInject constructor(
    private val stationsUseCase: IStationsUseCase
) : ViewModel() {
    private var isFirstFocused = true
    private var ignoreChange = false
    private var searchQuery = ""

    private val _viewState: MutableLiveData<StationPickerUiState> = MutableLiveData()
    val viewState: LiveData<StationPickerUiState> = _viewState

    private val _stationsData = MutableLiveData<List<StationInfoModel>>()
    val stationsData: LiveData<List<StationInfoModel>> = _stationsData

    private val ioContext = viewModelScope + exceptionHandler + IO

    fun setStation(station: StationInfoModel) {
        ignoreChange = true
        _viewState.postValue(
            if (isFirstFocused) _viewState.value.getValue()
                .copy(firstStation = station)
            else _viewState.value.getValue().copy(secondStation = station)
        )
    }

    fun setCurrentFocus(firstStationFocused: Boolean) {
        isFirstFocused = firstStationFocused
    }

    fun getStationsData(position: Int = 0) = ioContext.launch(IO) {
        _stationsData.postValue(stationsUseCase.getStationsData(searchQuery, position))
    }

    @Suppress("UNUSED_PARAMETER")
    fun onTextChanged(
        text: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        if (!ignoreChange) {
            ioContext.launch {
                searchQuery = text.toString()
                _stationsData.postValue(stationsUseCase.getStationsData(searchQuery, 0))
            }
        } else {
            ignoreChange = false
        }

    }
}