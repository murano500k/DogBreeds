package com.murano500k.dogbreeds

import com.google.gson.JsonObject
import com.murano500k.dogbreeds.model.ResponseMultipleImages
import com.murano500k.dogbreeds.model.ResponseSingleImage
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): JsonObject

    @GET("breed/{breed}/images/random")
    suspend fun getBreedRandomImage(@Path(value = "breed",encoded = true) breed : String): ResponseSingleImage

    @GET("breed/{breed}/images")
    suspend fun getAllBreedImages(@Path(value = "breed",encoded = true) breed: String): ResponseMultipleImages

}