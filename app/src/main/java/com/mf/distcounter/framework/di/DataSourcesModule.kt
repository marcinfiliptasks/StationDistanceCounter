package com.mf.distcounter.framework.di

import com.mf.data.dataSources.stationsDataSources.StationsLocalDataSource
import com.mf.data.dataSources.stationsDataSources.StationsRemoteDataSource
import com.mf.domain.dataSources.IStationsLocalDataSource
import com.mf.domain.dataSources.IStationsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataSourcesModule {
    @Binds
    @Singleton
    abstract fun bindsStationsRemoteDataSource(dataSource: StationsRemoteDataSource): IStationsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsStationsLocalDataSource(dataSource: StationsLocalDataSource): IStationsLocalDataSource
}