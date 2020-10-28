package com.murano500k.dogbreeds.di

import android.app.Application
import androidx.room.Room
import com.murano500k.dogbreeds.persistence.AppDatabase
import com.murano500k.dogbreeds.persistence.DogBreedDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application,
  ): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, "dog.db")
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun provideDogBreedDao(appDatabase: AppDatabase): DogBreedDao {
    return appDatabase.dogBreedDao()
  }

}
