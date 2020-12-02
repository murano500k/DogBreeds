package com.murano500k.dogbreeds

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.murano500k.dogbreeds.databinding.BreedItemWithLabelBinding
import com.murano500k.dogbreeds.fragment.BreedImagesFragment
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.utils.DogDiffCallback

class BreedListAdapter : ListAdapter<DogBreed,BreedListAdapter.DiffUtilDogBreedViewHolder> (
    DogDiffCallback()
){



    class DiffUtilDogBreedViewHolder(val binding: BreedItemWithLabelBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: DiffUtilDogBreedViewHolder, position: Int) {
        holder.binding.apply {
            dogBreed = getItem(position)
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffUtilDogBreedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<BreedItemWithLabelBinding>(inflater, R.layout.breed_item_with_label, parent, false)
        return DiffUtilDogBreedViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                val dogBreed = getItem(position)
                Navigation.findNavController(itemView).navigate(
                    R.id.action_listFragment_to_breedImagesFragment,
                    bundleOf(BreedImagesFragment.ARG_DOG_BREED to dogBreed)
                )
            }
        }
    }
}