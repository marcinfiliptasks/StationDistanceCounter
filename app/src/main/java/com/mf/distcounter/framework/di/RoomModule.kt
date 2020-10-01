package com.mf.distcounter.framework.di

import android.content.Context
import androidx.room.Room
import com.mf.distcounter.framework.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "mftask.db"
    ).build()

    @Provides
    @Singleton
    fun providesStationKeywordDao(database: AppDatabase) = database.stationKeywordsDao()

    @Provides
    @Singleton
    fun providesStationDao(database: AppDatabase) = database.stationDao()

    @Provides
    @Singleton
    fun providesStationsViewDao(database: AppDatabase) = database.stationsViewDao()

    @Provides
    @Singleton
    fun providesSyncRecordDao(database: AppDatabase)=database.syncRecordDao()
}