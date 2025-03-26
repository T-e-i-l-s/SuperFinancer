package com.mustafin.analytics.di

import com.mustafin.analytics.data.appMetrica.eventConstructor.EventConstructor
import com.mustafin.analytics.data.appMetrica.eventConstructor.EventConstructorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AnalyticsModule {
    @Provides
    @Singleton
    fun provideEventConstructor(): EventConstructor {
        return EventConstructorImpl()
    }
}