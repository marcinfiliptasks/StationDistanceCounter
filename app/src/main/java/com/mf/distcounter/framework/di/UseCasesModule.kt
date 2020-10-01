package com.mf.distcounter.framework.di

import com.mf.distcounter.framework.location.DistanceCalculator
import com.mf.domain.usecases.distanceCalculator.IDistanceCalculatorUseCase
import com.mf.domain.usecases.stations.IStationsUseCase
import com.mf.domain.usecases.stations.StationsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class UseCasesModule {
    @Binds
    @Singleton
    abstract fun bindStationsUseCase(stationsUseCase: StationsUseCase): IStationsUseCase

    @Binds
    @Singleton
    abstract fun bindLocationUseCase(locationsUseCase: DistanceCalculator): IDistanceCalculatorUseCase
}