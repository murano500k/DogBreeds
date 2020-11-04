package com.murano500k.dogbreeds

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.murano500k.dogbreeds.databinding.BreedItemWithLabelBinding
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.fragment.BreedImagesFragment.Companion.ARG_DOG_BREED


class BreedListAdapter : RecyclerView.Adapter<BreedListAdapter.DogBreedViewHolder>() {

    private val items: MutableList<DogBreed> = mutableListOf()
    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<BreedItemWithLabelBinding>(inflater, R.layout.breed_item_with_label, parent, false)
        return DogBreedViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                val dogBreed = items.get(position)
                Navigation.findNavController(itemView).navigate(
                    R.id.action_listFragment_to_breedImagesFragment,
                    bundleOf(ARG_DOG_BREED to dogBreed)
                )
            }
        }
    }

    fun addDogBreedList(dogBreedList: List<DogBreed>) {
        Log.d(TAG, "addDogBreedList() called with: dogBreedList = ${dogBreedList.size}")
        val previous = items.size
        items.addAll(dogBreedList)
        notifyItemRangeChanged(previous, dogBreedList.size)
    }

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        holder.binding.apply {
            dogBreed = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class DogBreedViewHolder(val binding: BreedItemWithLabelBinding) :
        RecyclerView.ViewHolder(binding.root)
}