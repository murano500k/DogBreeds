package com.murano500k.dogbreeds.model

import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.murano500k.dogbreeds.TAG
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "breed_table")
data class DogBreed(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
                    @ColumnInfo(name = "breed") val breed: String,
                    @ColumnInfo(name = "subbreed") var subbreed : String,
                    @ColumnInfo(name = "imageUrl") var imageUrl : String
) : Parcelable {


    override fun toString(): String {
        return "$subbreed $breed"
    }

    fun getApiParam(): String {
        Log.w(TAG, "getApiParam: $breed/$subbreed" )

        return if(subbreed.isEmpty()) breed
        else "$breed/$subbreed"
    }
}