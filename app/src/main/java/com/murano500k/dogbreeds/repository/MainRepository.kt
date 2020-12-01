package com.murano500k.dogbreeds.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.google.gson.JsonObject
import com.murano500k.dogbreeds.ApiService
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.persistence.DogBreedDao
import com.murano500k.dogbreeds.repository.TypeConverter.Companion.parseListBreeds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val dogBreedDao: DogBreedDao
)  {

    suspend fun fetchDogBreedsListSimple(): List<DogBreed>{
        var dogBreeds = dogBreedDao.getDogBreedList()
        Log.w(TAG, "fetchDogBreedList: dogBreeds.size = ${dogBreeds.size}", )

        if (dogBreeds.isEmpty()) {
            Log.w(TAG, "fetchDogBreedList: isEmpty", )
            dogBreeds = parseListBreeds(apiService.getAllBreeds())
            dogBreeds.map{
                it.imageUrl = fetchRandomBreedImageUrl(it)
            }
            dogBreedDao.insertDogBreedList(dogBreeds)
            //onError("Fetching from network is not implemented")
        }
        Log.w(TAG, "fetchDogBreedList: dogBreeds.size = ${dogBreeds.size}", )
        return dogBreeds
    }

    suspend fun fetchRandomBreedImageUrl(dogBreed: DogBreed): String{
        val responseSingleImage =
            if(dogBreed.subbreed.isEmpty()) apiService.getBreedRandomImage(dogBreed.breed)
            else apiService.getSubbreedRandomImage(dogBreed.breed, dogBreed.subbreed)
        return if(responseSingleImage.status=="success"){
            responseSingleImage.message
        }else ""
    }

    suspend fun getAllBreedImages(dogBreed: DogBreed): List<DogBreed>{
        val responseMultipleImages =
            if(dogBreed.subbreed.isEmpty()) apiService.getAllBreedImages(dogBreed.breed)
            else apiService.getAllSubbreedImages(dogBreed.breed, dogBreed.subbreed)
        return if(responseMultipleImages.status=="success"){
            responseMultipleImages.message.map { DogBreed(0,dogBreed.breed, dogBreed.subbreed, it) }
        }else return listOf()
    }













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



    private fun getDogBreedsTest(): List<DogBreed> {
        val list = ArrayList<DogBreed>()

        list.add(DogBreed(0,"affenpinscher", "",""))
        list.add(DogBreed(0,"african", "",""))
        list.add(DogBreed(0,"akita", "",""))
        list.add(DogBreed(0,"australian", "shepherd",""))
        list.add(DogBreed(0,"bulldog", "boston",""))
        list.add(DogBreed(0,"bulldog", "english",""))
        list.add(DogBreed(0,"boxer", "",""))

        return list
    }

}
