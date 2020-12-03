package com.murano500k.dogbreeds.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.murano500k.dogbreeds.BreedListAdapter
import com.murano500k.dogbreeds.R
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.databinding.FragmentListBreedsBinding
import com.murano500k.dogbreeds.viewmodel.ListBreedsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class BreedListFigment : Fragment() {

    @VisibleForTesting
    private val breedsViewModel: ListBreedsViewModel by viewModels()


    private lateinit var breedListAdapter: BreedListAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.v(TAG, "onCreateView: ")
        val binding: FragmentListBreedsBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_list_breeds, container, false
        )
        binding.vm = breedsViewModel
        breedListAdapter = BreedListAdapter()
        binding.adapter = breedListAdapter
        breedsViewModel.listBreedsLiveDataFromDb.observe(viewLifecycleOwner, Observer {
            breedListAdapter.submitList(it)
        })
        return binding.root
    }
}