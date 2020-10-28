package com.murano500k.dogbreeds

import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.model.ResponceSingleImage
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Call<DogBreed>

    @GET("breed/{breed}/images/random")
    suspend fun getBreedRandomImage(@Path("breed") breed : String): ResponceSingleImage
/*
    @GET("breed/{breed}/images")
    suspend fun getBreedImages(@Path("breed") breed: String): Call*/
}