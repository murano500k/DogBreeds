package com.murano500k.dogbreeds.binding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.murano500k.dogbreeds.DogBreedAdapter
import com.murano500k.dogbreeds.R
import com.murano500k.dogbreeds.model.DogBreed

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
  view.adapter = adapter
}
/*
@BindingAdapter("paginationPokemonList")
fun paginationPokemonList(view: RecyclerView, viewModel: MainViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.isLoading.get() },
    loadMore = { viewModel.fetchPokemonList(it) },
    onLast = { false }
  ).run {
    threshold = 8
  }
}
*/
@BindingAdapter("adapterDogBreedList")
fun bindAdapterDogBreedList(view: RecyclerView, dogBreedList: List<DogBreed>?) {
    Log.w("TAG", "bindAdapterPokemonList: ${dogBreedList?.size}" )
    if(!dogBreedList.isNullOrEmpty()){
      (view.adapter as? DogBreedAdapter)?.addDogBreedList(dogBreedList)
    }
}


