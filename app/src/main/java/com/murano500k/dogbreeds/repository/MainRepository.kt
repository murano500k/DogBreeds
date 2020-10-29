package com.murano500k.dogbreeds.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.google.gson.JsonObject
import com.murano500k.dogbreeds.ApiService
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.persistence.DogBreedDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val dogBreedDao: DogBreedDao
)  {


    suspend fun breedsFromApi(){
        val result = apiService.getAllBreeds()
        parseListBreeds(result)
        //Log.w(TAG, "breedsFromApi: ${result.toString()}" )
    }

    private fun parseListBreeds(jsonObject: JsonObject): List<DogBreed>{
        var list = ArrayList<DogBreed>()
        var message = jsonObject.get("message").asJsonObject
        message.keySet().forEach{breed ->
            //Log.w(TAG, "parseListBreeds: $breed" )
            var subbreeds = message.getAsJsonArray(breed)
            //Log.w(TAG, "parseListSubbreeds: ${subbreeds.size()}" )
            if(subbreeds.size()>0){
                subbreeds.forEach{ subbreed ->
                    //Log.w(TAG, "        subbreed: $subbreed" )
                    subbreed.asString
                    list.add(DogBreed(0, breed,subbreed.asString,""))
                }
            }else{
                list.add(DogBreed(0, breed, "", ""))
            }

        }
        list.forEach{
            Log.w(TAG, "parseListBreeds: ${it.breed} ${it.subbreed}" )
        }
        return list
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


    @WorkerThread
    suspend fun fetchDogBreedList(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        var dogBreeds = dogBreedDao.getDogBreedList()
        Log.w(TAG, "fetchDogBreedList: dogBreeds.size = ${dogBreeds.size}", )

        if (dogBreeds.isEmpty()) {
            Log.w(TAG, "fetchDogBreedList: isEmpty", )
            dogBreeds = parseListBreeds(apiService.getAllBreeds())
            dogBreedDao.insertDogBreedList(dogBreeds)
            //onError("Fetching from network is not implemented")
        }
        Log.w(TAG, "fetchDogBreedList: dogBreeds.size = ${dogBreeds.size}", )

        fetchUrls(dogBreeds)
        emit(dogBreeds)
        onSuccess()
    }.flowOn(Dispatchers.IO)


    suspend fun fetchDogBreedsListSimple(): List<DogBreed>{
        var dogBreeds = dogBreedDao.getDogBreedList()
        Log.w(TAG, "fetchDogBreedList: dogBreeds.size = ${dogBreeds.size}", )

        if (dogBreeds.isEmpty()) {
            Log.w(TAG, "fetchDogBreedList: isEmpty", )
            dogBreeds = parseListBreeds(apiService.getAllBreeds())
            dogBreedDao.insertDogBreedList(dogBreeds)
            //onError("Fetching from network is not implemented")
        }
        Log.w(TAG, "fetchDogBreedList: dogBreeds.size = ${dogBreeds.size}", )
        //fetchUrls(dogBreeds)
        return dogBreeds
    }


    private suspend fun fetchUrls(dogBreeds: List<DogBreed>) {
        dogBreeds.forEach { dogBreed ->
            Log.w(TAG, "fetchUrls: $dogBreed ${dogBreed.imageUrl}", )
            dogBreed.imageUrl = fetchRandomBreedImageUrl(dogBreed)
        }
    }

    private suspend fun fetchRandomBreedImageUrl(dogBreed: DogBreed): String{
        val responseSingleImage =
                if(dogBreed.subbreed.isEmpty()) apiService.getBreedRandomImage(dogBreed.breed)
                else apiService.getSubbreedRandomImage(dogBreed.breed, dogBreed.subbreed)
        return if(responseSingleImage.status=="success"){
            responseSingleImage.message
        }else ""
    }


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

    suspend fun getAllBreedImages(dogBreed: DogBreed): List<DogBreed>{
        val responseMultipleImages =
            if(dogBreed.subbreed.isEmpty()) apiService.getAllBreedImages(dogBreed.breed)
            else apiService.getAllSubbreedImages(dogBreed.breed, dogBreed.subbreed)
        return if(responseMultipleImages.status=="success"){
            responseMultipleImages.message.map { DogBreed(0,dogBreed.breed, dogBreed.subbreed, it) }
        }else return listOf()
    }
}
