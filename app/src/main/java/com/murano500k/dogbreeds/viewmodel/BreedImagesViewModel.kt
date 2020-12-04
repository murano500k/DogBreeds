package com.murano500k.dogbreeds.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.persistence.DogBreedDao
import com.murano500k.dogbreeds.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BreedImagesViewModel @ViewModelInject constructor(
                           private val mainRepository: MainRepository,
                           @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState>
        get() = _loading

    val allBreedImagesLiveData = getSavedDogBreed().switchMap {
        mainRepository.getAllBreedImagesLiveData(it)
    }

    fun setSelectedDogBreed(breed: DogBreed){
        savedStateHandle.set(DOG_BREED_SAVED_STATE_KEY,breed.breed)
    }

    private fun getSavedDogBreed(): MutableLiveData<String> {
        return savedStateHandle.getLiveData(DOG_BREED_SAVED_STATE_KEY, NO_DOG_BREED)
    }



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


    companion object {
        private const val NO_DOG_BREED = "not_set"
        private const val DOG_BREED_SAVED_STATE_KEY = "DOG_BREED_SAVED_STATE_KEY"
    }
}