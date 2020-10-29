package com.murano500k.dogbreeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.murano500k.dogbreeds.databinding.MyItemLayoutBinding
import com.murano500k.dogbreeds.fragment.BreedImagesFragment
import com.murano500k.dogbreeds.model.DogBreed

class BreedImagesAdapter : ListAdapter<DogBreed, BreedImagesAdapter.BreedImagesViewHolder>(Companion) {

    class BreedImagesViewHolder(val binding: MyItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    companion object: DiffUtil.ItemCallback<DogBreed>() {
        override fun areItemsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedImagesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MyItemLayoutBinding.inflate(layoutInflater)
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
    }
}