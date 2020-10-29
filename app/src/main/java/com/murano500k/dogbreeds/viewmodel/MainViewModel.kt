package com.murano500k.dogbreeds.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.murano500k.dogbreeds.TAG
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
    }

}