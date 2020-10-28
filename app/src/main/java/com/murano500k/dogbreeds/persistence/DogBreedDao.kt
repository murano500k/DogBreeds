package com.murano500k.dogbreeds.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murano500k.dogbreeds.model.DogBreed

@Dao
interface DogBreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreedList(dogbreedList: List<DogBreed>)

    @Query("SELECT * FROM breed_table ")
    suspend fun getDogBreedList(): List<DogBreed>
}
