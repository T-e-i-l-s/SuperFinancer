package com.mustafin.feed.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mustafin.feed.data.repositories.eventsRepository.EventsRepository
import com.mustafin.feed.data.repositories.eventsRepository.EventsRepositoryImpl
import com.mustafin.feed.data.repositories.favoritesRepository.FavoritesRepository
import com.mustafin.feed.data.repositories.favoritesRepository.FavoritesRepositoryImpl
import com.mustafin.feed.data.source.local.database.FeedDatabase
import com.mustafin.feed.data.source.local.eventsSource.EventsDao
import com.mustafin.feed.data.source.local.favoritesSource.FavoritesSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedModule {
    @Provides
    @Singleton
    fun provideFeedDatabase(@ApplicationContext context: Context): FeedDatabase {
        return Room.databaseBuilder(
            context,
            FeedDatabase::class.java,
            "financer_feed_db"
        ).build()
    }

    @Provides
    @Singleton
    @Named("encryptedPrefs")
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            "financer_feed_secure_prefs",
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    // Источники данных
    @Provides
    @Singleton
    fun provideEventsDao(feedDatabase: FeedDatabase): EventsDao {
        return feedDatabase.eventsDao()
    }

    @Provides
    @Singleton
    fun provideFavoritesSource(
        @Named("encryptedPrefs") sharedPreferences: SharedPreferences
    ): FavoritesSource {
        return FavoritesSource(sharedPreferences)
    }


    // Репозитории
    @Provides
    @Singleton
    fun provideEventsRepository(eventsDao: EventsDao): EventsRepository {
        return EventsRepositoryImpl(eventsDao)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(
        eventsDao: EventsDao,
        favoritesSource: FavoritesSource
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(eventsDao, favoritesSource)
    }
}