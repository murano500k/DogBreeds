package com.murano500k.dogbreeds

import android.util.Log
import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.murano500k.dogbreeds.base.LiveCoroutinesViewModel
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.repository.MainRepository
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {


    var listBreedsLiveData: LiveData<List<DogBreed>> = MutableLiveData()

    var dogImageUrlLiveData: LiveData<String> = MutableLiveData()

    val isLoading: ObservableBoolean = ObservableBoolean(false)


    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()

    val toastLiveData: LiveData<String> get() = _toastLiveData

    init {

        Timber.d("init MainViewModel")
        /*pokemonInfoLiveData = launchOnViewModelScope {
            isLoading.set(true)
            detailRepository.fetchPokemonInfo(
                name = pokemonName,
                onSuccess = { isLoading.set(false) },
                onError = { _toastLiveData.postValue(it) }
            ).asLiveData()
        }*/
        listBreedsLiveData = launchOnViewModelScope {
            isLoading.set(true)
            mainRepository.fetchDogBreedList(
                onSuccess = {
                    isLoading.set(false) },
                onError = {_toastLiveData.postValue(it) }
            ).asLiveData()
        }
        getDogImage("akita")


    }

    fun getDogImage(breed: String){
        dogImageUrlLiveData = launchOnViewModelScope {
            isLoading.set(true)
            mainRepository.fetchDogImageUrl(
                breed,
                onSuccess = {
                    Log.w(TAG, "getDogImage: success", )
                    isLoading.set(false)
                },
                onError = {
                    Log.w(TAG, "getDogImage: error", )
                    _toastLiveData.postValue(it)
                }
            ).asLiveData()
        }
    }

    fun getDogImage(breed: DogBreed){
        getDogImage(breed.breed)
    }


}