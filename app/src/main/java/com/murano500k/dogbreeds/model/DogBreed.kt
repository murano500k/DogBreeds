package com.murano500k.dogbreeds.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "breed_table")
data class DogBreed(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
                    @ColumnInfo(name = "breed") val breed: String,
                    @ColumnInfo(name = "subbreed") var subbread : String
) : Parcelable {

    override fun toString(): String {
        return "$subbread $breed"
    }
}