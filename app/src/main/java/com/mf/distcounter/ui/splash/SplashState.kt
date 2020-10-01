package com.mf.distcounter.ui.splash

data class SplashState(
    val keywordsDone: Boolean = false,
    val stationsDone: Boolean = false,
    val goForward: Boolean = false,
    val dateNotNull: Boolean = false,
    val failure: Boolean = false
)