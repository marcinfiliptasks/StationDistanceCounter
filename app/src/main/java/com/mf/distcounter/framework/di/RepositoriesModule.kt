package com.mf.distcounter.framework.di

import com.mf.data.repositories.StationsRepository
import com.mf.domain.repositories.IStationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindStationsRepo(stationsRepository: StationsRepository):IStationsRepository
}