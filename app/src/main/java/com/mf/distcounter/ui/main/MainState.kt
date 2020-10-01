package com.mf.distcounter.ui.main

data class MainState(
    val fetchingApiData: Boolean = false,
    val fetchingApiError: Boolean= false,
    val geoCoderError: Boolean = false
)

