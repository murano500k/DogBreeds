 package com.murano500k.dogbreeds

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import dagger.hilt.android.AndroidEntryPoint


 @AndroidEntryPoint
class MainActivity : AppCompatActivity() {
     @VisibleForTesting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         supportActionBar?.setDisplayHomeAsUpEnabled(true)
         supportActionBar?.setDisplayShowHomeEnabled(true);

         setContentView(R.layout.activity_main)
     }
     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when (item.getItemId()) {
             android.R.id.home -> {
                 onBackPressed()
                 true
             }
             else -> super.onOptionsItemSelected(item)
         }
     }
}