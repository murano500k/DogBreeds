package com.murano500k.dogbreeds.binding

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.murano500k.dogbreeds.BreedListAdapter
import com.murano500k.dogbreeds.model.DogBreed

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
  view.adapter = adapter
}
@BindingAdapter("adapterDogBreedList")
fun bindAdapterDogBreedList(view: RecyclerView, dogBreedList: List<DogBreed>?) {
    Log.w("TAG", "bindAdapterPokemonList: ${dogBreedList?.size}" )
    if(!dogBreedList.isNullOrEmpty()){
      (view.adapter as? BreedListAdapter)?.addDogBreedList(dogBreedList)
    }
}


