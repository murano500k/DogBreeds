package com.murano500k.dogbreeds.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.murano500k.dogbreeds.ApiService
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.persistence.DogBreedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val dogBreedDao: DogBreedDao
)  {


    @WorkerThread
    suspend fun fetchDogImageUrl(
        breedName: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        var responceSingleImage = apiService.getBreedRandomImage(breedName)
        Log.w(TAG, "fetchDogImageUrl: ${responceSingleImage.message}" )
        if(responceSingleImage.status!="success") onError("error")
        else {
            emit(responceSingleImage.message)
            onSuccess()
        }
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    suspend fun fetchDogBreedList(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        var dogBreeds = dogBreedDao.getDogBreedList()
        if (dogBreeds.isEmpty()) {
            dogBreeds = getDogBreedsTest()
            dogBreedDao.insertDogBreedList(dogBreeds)
            onSuccess()
            //onError("Fetching from network is not implemented")
        } else {
            emit(dogBreeds)
            onSuccess()
        }
    }.flowOn(Dispatchers.IO)



    private fun getDogBreedsTest(): List<DogBreed> {
        val list = ArrayList<DogBreed>()

        list.add(DogBreed(0,"affenpinscher", ""))
        list.add(DogBreed(0,"african", ""))
        list.add(DogBreed(0,"akita", ""))
        list.add(DogBreed(0,"australian", "shepherd"))
        list.add(DogBreed(0,"bulldog", "boston"))
        list.add(DogBreed(0,"bulldog", "english"))
        list.add(DogBreed(0,"boxer", ""))

        return list
    }


}
