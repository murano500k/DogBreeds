package com.murano500k.dogbreeds.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.murano500k.dogbreeds.R
import com.murano500k.dogbreeds.databinding.FragmentBreedImagesBinding
import com.murano500k.dogbreeds.databinding.FragmentSingleImageBinding
import com.murano500k.dogbreeds.fragment.BreedImagesFragment.Companion.ARG_DOG_BREED
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class SingleImageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSingleImageBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_single_image, container, false
        )

        binding.dogBreed = arguments?.getParcelable(ARG_DOG_BREED)
        return binding.root
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_image, container, false)
    }
}
//val dogBreed: DogBreed? = arguments?.getParcelable(ARG_DOG_BREED)