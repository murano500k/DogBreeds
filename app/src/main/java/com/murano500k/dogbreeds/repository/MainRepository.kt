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

    fun getListBreedsLiveData() = dogBreedDao.getDogBreedListLiveData()

    suspend fun fetchUpdatedDogBreedsList(){
        if(dogBreedDao.getDogBreedList().isEmpty()){
            dogBreedDao.insertDogBreedList(parseListBreeds(apiService.getAllBreeds()))
        }
        dogBreedDao.getDogBreedList().forEach{
            it.imageUrl = fetchRandomBreedImageUrl(it)
            dogBreedDao.insertDogBreed(it)
        }
    }

    suspend fun fetchRandomBreedImageUrl(dogBreed: DogBreed): String{
        Log.w(TAG, "fetchRandomBreedImageUrl: "+dogBreed.breed )
        val responseSingleImage = apiService.getBreedRandomImage(dogBreed.breed)
            //if(dogBreed.subbreed.isEmpty()) apiService.getBreedRandomImage(dogBreed.breed)
            //else apiService.getSubbreedRandomImage(dogBreed.breed, dogBreed.subbreed)

        return if(responseSingleImage.status=="success"){
            responseSingleImage.message
        }else ""
    }

    suspend fun getAllBreedImages(dogBreed: DogBreed): List<DogBreed>{
        val responseMultipleImages = apiService.getAllBreedImages(dogBreed.breed)
            //if(dogBreed.subbreed.isEmpty()) apiService.getAllBreedImages(dogBreed.breed)
            //else apiService.getAllSubbreedImages(dogBreed.breed, dogBreed.subbreed)
        return if(responseMultipleImages.status=="success"){
            responseMultipleImages.message.map { DogBreed(0,dogBreed.breed, it) }
        }else return listOf()
    }












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
