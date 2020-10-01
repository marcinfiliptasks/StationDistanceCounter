package com.mf.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class SyncRecordEntity(
    @PrimaryKey val id: Long = 0,
    val date: Date
)