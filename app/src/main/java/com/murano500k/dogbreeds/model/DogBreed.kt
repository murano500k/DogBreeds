package com.murano500k.dogbreeds.model

import android.net.Uri
import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.murano500k.dogbreeds.TAG
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "breed_table")
data class DogBreed(@ColumnInfo(name = "breed") val breed: String,
                    @PrimaryKey @ColumnInfo(name = "imageUrl") var imageUrl : String
) : Parcelable {


    override fun toString(): String {
        return if(breed.contains("/")){
            val array = breed.split("/")
            "${array[1]} ${array[0]}"
        }else breed
    }

    fun getImageName(): String {
        return Uri.parse(imageUrl).lastPathSegment ?: "null"
    }

}