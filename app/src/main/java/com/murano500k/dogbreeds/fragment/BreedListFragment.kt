package com.murano500k.dogbreeds.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.murano500k.dogbreeds.DogBreedAdapter
import com.murano500k.dogbreeds.R
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.databinding.ActivityMainBinding
import com.murano500k.dogbreeds.databinding.FragmentListBinding
import com.murano500k.dogbreeds.viewmodel.ListBreedsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class BreedListFragment : Fragment() {

    @VisibleForTesting
    private val breedsViewModel: ListBreedsViewModel by viewModels()

/*
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).run {
            lifecycleOwner = this@MainActivity
            viewModel = userViewModel
        }
    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v(TAG, "onCreateView: " )
        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_list, container, false)
        binding.vm = breedsViewModel
        binding.adapter = DogBreedAdapter()
        breedsViewModel.listBreedsLiveData.observe(viewLifecycleOwner, Observer {
            val adapter = DogBreedAdapter()
            adapter.addDogBreedList(it)
            binding.adapter=adapter
        })
        breedsViewModel.breedsFromApi()
        return binding.root
    }

}