package com.murano500k.dogbreeds.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BreedImagesViewModel @ViewModelInject constructor(
                           private val mainRepository: MainRepository
) : ViewModel() {

    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState>
        get() = _loading

    private val _data = MutableLiveData<List<DogBreed>>()
    val data: LiveData<List<DogBreed>>
        get() = _data


    fun fetchBreedImages(breed: DogBreed) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(LoadingState.LOADING)
                val response = mainRepository.getAllBreedImages(breed)
                if(response.isNullOrEmpty()) _loading.postValue(LoadingState.error("ERROR"))
                else {
                    _data.postValue(response)
                    _loading.postValue(LoadingState.LOADED)
                }
            } catch (e: Exception) {
                _loading.postValue(LoadingState.error(e.message))
            }
        }
    }
}