package com.mf.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.mf.data.database.views.StationView

@Dao
interface StationsViewDao {
    @Query(
        "SELECT * FROM StationView WHERE keyword LIKE '%' || :searchQuery  || '%'" +
                " ORDER BY hits DESC LIMIT :itemCount"
    )
    fun getStationsData(itemCount: Int, searchQuery: String): List<StationView>
}