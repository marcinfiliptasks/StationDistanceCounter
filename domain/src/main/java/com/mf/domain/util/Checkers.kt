package com.mf.domain.util

import com.mf.domain.consts.CACHE_TIME
import java.util.*

fun checkIfHaveToRefresh(checkLastSync: Date?) = checkLastSync?.let {
    it.time + CACHE_TIME < Date().time
} ?: true