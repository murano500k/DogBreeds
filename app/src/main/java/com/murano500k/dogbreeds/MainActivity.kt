 package com.murano500k.dogbreeds

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


 @AndroidEntryPoint
class MainActivity : AppCompatActivity() {
     @VisibleForTesting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         supportActionBar?.setDisplayHomeAsUpEnabled(true)
     }
}