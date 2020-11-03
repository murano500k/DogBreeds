package com.murano500k.dogbreeds.repository

import android.util.Log
import com.google.gson.JsonObject
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.model.DogBreed

class TypeConverter {
    companion object {
        fun parseListBreeds(jsonObject: JsonObject): List<DogBreed>{
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
    }
}