package com.murano500k.dogbreeds.di

import com.murano500k.dogbreeds.ApiService
import com.murano500k.dogbreeds.persistence.DogBreedDao
import com.murano500k.dogbreeds.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

  @Provides
  @ActivityRetainedScoped
  fun provideMainRepository(
    apiService: ApiService,
    dogBreedDao: DogBreedDao
  ): MainRepository {
    return MainRepository(apiService, dogBreedDao)
  }

}