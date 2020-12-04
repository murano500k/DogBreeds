package com.murano500k.dogbreeds.utils

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.murano500k.dogbreeds.model.DogBreed

class DogDiffCallback : DiffUtil.ItemCallback<DogBreed>() {
    override fun areItemsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
        return oldItem == newItem
    }
}