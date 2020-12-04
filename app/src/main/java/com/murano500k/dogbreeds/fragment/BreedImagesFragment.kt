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
import com.murano500k.dogbreeds.BreedImagesAdapter
import com.murano500k.dogbreeds.R
import com.murano500k.dogbreeds.TAG
import com.murano500k.dogbreeds.databinding.FragmentBreedImagesBinding
import com.murano500k.dogbreeds.model.DogBreed
import com.murano500k.dogbreeds.viewmodel.BreedImagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


@AndroidEntryPoint
@FragmentScoped
class BreedImagesFragment : Fragment() {
    companion object{
        const val ARG_DOG_BREED = "arg_dog_breed"
    }
    @VisibleForTesting
    private val viewModel: BreedImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dogBreed: DogBreed? = arguments?.getParcelable(ARG_DOG_BREED)
        dogBreed?.let {
            viewModel.setSelectedDogBreed(it)
            viewModel.fetchBreedImages(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBreedImagesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_breed_images, container, false
        )

        val imagesAdapter = BreedImagesAdapter()

        binding.adapter = imagesAdapter

        viewModel.allBreedImagesLiveData.observe(viewLifecycleOwner, Observer {
            Log.w(TAG, "observe: size= ${it.size}")
            it.let(imagesAdapter::submitList)
        })

        return binding.root
    }

}