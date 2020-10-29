package com.murano500k.dogbreeds.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.base.LiveCoroutinesViewModel
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListBreedsViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {
    fun breedsFromApi() {

        viewModelScope.launch (Dispatchers.IO) {
            mainRepository.breedsFromApi()
        }
    }

    var listBreedsLiveData: MutableLiveData<List<DogBreed>> = MutableLiveData()
    val isLoading: ObservableBoolean = ObservableBoolean(false)
    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Log.w(TAG, "init: ", )

        viewModelScope.launch(Dispatchers.IO){
            Log.w(TAG, "launch: ", )
            listBreedsLiveData.postValue(mainRepository.fetchDogBreedsListSimple())
        }
    }
}