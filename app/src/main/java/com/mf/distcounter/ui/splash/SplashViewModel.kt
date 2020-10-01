package com.mf.distcounter.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mf.domain.usecases.stations.IStationsUseCase
import com.mf.domain.util.checkIfHaveToRefresh
import com.mf.domain.util.exceptionHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import java.util.Date


class SplashViewModel @ViewModelInject constructor(
    private val stationsUseCase: IStationsUseCase
) : ViewModel() {
    private val _state: MutableLiveData<SplashState> = MutableLiveData()
    val state: LiveData<SplashState> = _state
    private val ioContext = viewModelScope + IO + exceptionHandler

    fun checkDb() = ioContext.launch {
        if (checkIfHaveToRefresh(stationsUseCase.checkLastSync())) {
            stationsUseCase.clearTables()
            fetchStationsFromApi()
            fetchKeywordsFromApi()
        } else {
            _state.postValue(SplashState(goForward = true))
        }
    }

    private suspend fun fetchStationsFromApi() = withContext(IO) {
        launch {
            stationsUseCase.getStations()
        }.invokeOnCompletion {
            processResult(true, it)
        }
    }

    private suspend fun fetchKeywordsFromApi() = withContext(IO) {
        launch {
            stationsUseCase.getStationKeywords()
        }.invokeOnCompletion {
            processResult(false,it)
        }
    }


    private fun processResult(isStationResult: Boolean, it: Throwable?) =
        _state.postValue(it?.let { SplashState(goForward = true )} ?:
            if (isStationResult) _state.get()
                .copy(stationsDone = true) else _state.get().copy(keywordsDone = true)
        )


    private fun MutableLiveData<SplashState>.get() = this.value ?: SplashState()
    fun setSyncDate() =
        ioContext.launch { stationsUseCase.setLastSync(Date()) }.invokeOnCompletion {
            _state.postValue(SplashState(goForward = true))
        }
}