package com.murano500k.dogbreeds.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListBreedsViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Log.w(TAG, "init: ", )
        viewModelScope.launch(Dispatchers.IO){
            Log.w(TAG, "launch: ", )
            mainRepository.fetchUpdatedDogBreedsList()
        }
    }

    var listBreedsLiveDataFromDb = mainRepository.getListBreedsLiveData().map {
        it.distinctBy { item -> item.breed }.sortedBy { dogBreed -> dogBreed.imageUrl }
    }


}