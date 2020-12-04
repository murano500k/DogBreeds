package com.murano500k.dogbreeds.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murano500k.dogbreeds.model.DogBreed

@Dao
interface DogBreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreedList(dogbreedList: List<DogBreed>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreed(dogbreed: DogBreed)

    @Query("SELECT * FROM breed_table ")
    fun getDogBreedListLiveData(): LiveData<List<DogBreed>>

    @Query("SELECT * FROM breed_table WHERE breed LIKE :breedArg")
    fun getAllBreedImagesLiveData(breedArg: String): LiveData<List<DogBreed>>

    @Query("SELECT COUNT(breed) FROM breed_table WHERE breed LIKE :breedArg")
    fun getBreedImagesCount(breedArg: String): Int



    @Query("SELECT COUNT(breed) FROM breed_table ")
    fun getAllItemsCount(): Int

}
