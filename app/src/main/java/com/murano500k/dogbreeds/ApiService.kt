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
    suspend fun getBreedRandomImage(@Path("breed") breed : String): ResponseSingleImage


    @GET("breed/{breed}/{subbreed}/images/random")
    suspend fun getSubbreedRandomImage(@Path("breed") breed : String,
                                       @Path("subbreed") subbreed : String): ResponseSingleImage

    @GET("breed/{breed}/images")
    suspend fun getAllBreedImages(@Path("breed") breed: String): ResponseMultipleImages

    @GET("breed/{breed}/{subbreed}/images")
    suspend fun getAllSubbreedImages(@Path("breed") breed : String,
                                       @Path("subbreed") subbreed : String): ResponseMultipleImages
}