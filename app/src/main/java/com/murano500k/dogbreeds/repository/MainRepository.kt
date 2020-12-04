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

    fun getAllBreedImagesLiveData(breed: String) = dogBreedDao.getAllBreedImagesLiveData(breed)

    suspend fun fetchUpdatedDogBreedsList(){
        parseListBreeds(apiService.getAllBreeds()).forEach{
            it.imageUrl = fetchRandomBreedImageUrl(it)
            Log.w(TAG, "imageUrl="+it.imageUrl)
            dogBreedDao.insertDogBreed(it)
        }
    }

    suspend fun fetchRandomBreedImageUrl(dogBreed: DogBreed): String{
        Log.w(TAG, "fetchRandomBreedImageUrl: "+dogBreed.breed )
        val responseSingleImage = apiService.getBreedRandomImage(dogBreed.breed)
        return if(responseSingleImage.status=="success"){
            responseSingleImage.message
        }else ""
    }

    suspend fun getAllBreedImages(dogBreed: DogBreed): List<DogBreed>{
        val responseMultipleImages = apiService.getAllBreedImages(dogBreed.breed)
        if(responseMultipleImages.status=="success"){
            val list = responseMultipleImages.message.map { DogBreed(dogBreed.breed, it) }
            dogBreedDao.insertDogBreedList(list)
            return list
        }else {
            return listOf()
        }
    }












    private fun getDogBreedsTest(): List<DogBreed> {
        val list = ArrayList<DogBreed>()

        list.add(DogBreed("affenpinscher", ""))
        list.add(DogBreed("african", ""))
        list.add(DogBreed("akita", ""))
        list.add(DogBreed("australian", "shepherd"))
        list.add(DogBreed("bulldog", "boston"))
        list.add(DogBreed("bulldog", "english"))
        list.add(DogBreed("boxer", ""))

        return list
    }

}
