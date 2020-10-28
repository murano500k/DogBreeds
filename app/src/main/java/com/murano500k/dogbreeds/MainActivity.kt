 package com.murano500k.dogbreeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.murano500k.dogbreeds.base.DataBindingActivity
import com.murano500k.dogbreeds.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


 @AndroidEntryPoint
class MainActivity : DataBindingActivity() {
     @VisibleForTesting
     val viewModel: MainViewModel by viewModels()
     private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        //onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }


        viewModel.listBreedsLiveData.observe(this, Observer { list ->
            list.forEach {
                Log.w(TAG, "onCreate: listBreedsLiveData $it")
                    //viewModel.getDogImage(breed = it)
            }
        })


        viewModel.dogImageUrlLiveData.observe(this, { url ->
            Log.w(TAG, "dogImageUrlLiveData: $url" )
            Glide.with(this)
                .load(url)
                .into(imageView)
        })
        viewModel.getDogImage("akita")



    }
}