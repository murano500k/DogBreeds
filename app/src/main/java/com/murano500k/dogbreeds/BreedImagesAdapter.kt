package com.murano500k.dogbreeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.murano500k.dogbreeds.databinding.BreedImageNoLabelBinding

import com.murano500k.dogbreeds.fragment.BreedImagesFragment
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.utils.DogDiffCallback

class BreedImagesAdapter : ListAdapter<DogBreed, BreedImagesAdapter.BreedImagesViewHolder>(
    DogDiffCallback()
) {

    class BreedImagesViewHolder(val binding: BreedImageNoLabelBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedImagesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BreedImageNoLabelBinding.inflate(layoutInflater)
        return BreedImagesViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                val dogBreed = getItem(position)
                Navigation.findNavController(itemView).navigate(
                    R.id.action_breedImagesFragment_to_singleImageFragment,
                    bundleOf(BreedImagesFragment.ARG_DOG_BREED to dogBreed)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BreedImagesViewHolder, position: Int) {
        val currentBreed = getItem(position)
        holder.binding.dogBreed = currentBreed
        holder.binding.executePendingBindings()
        val lp = holder.itemView.layoutParams
        if (lp is FlexboxLayoutManager.LayoutParams) {
            lp.flexGrow = 1f
        }
    }
}