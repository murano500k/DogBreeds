package com.murano500k.dogbreeds.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.murano500k.dogbreeds.model.DogBreed

@Database(entities = [DogBreed::class], version = 3, exportSchema = true)
//@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun dogBreedDao(): DogBreedDao
}