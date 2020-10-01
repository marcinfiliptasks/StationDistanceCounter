package com.mf.distcounter.framework.di

import com.mf.data.api.retrofit.retrofitServices.StationsRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder =
        Retrofit.Builder().baseUrl("https://koleo.pl/api/")
            .addConverterFactory(JacksonConverterFactory.create())

    @Singleton
    @Provides
    fun provideStationsService(retrofit: Retrofit.Builder) : StationsRetrofitService =
        retrofit.build().create(StationsRetrofitService::class.java)
}