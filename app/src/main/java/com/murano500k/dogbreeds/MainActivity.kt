 package com.murano500k.dogbreeds

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


 @AndroidEntryPoint
class MainActivity : AppCompatActivity() {
     private lateinit var navController: NavController


     @VisibleForTesting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
         val appBarConfiguration = AppBarConfiguration(setOf(R.id.listFragment))
         setupActionBarWithNavController(navController, appBarConfiguration)
     }

     override fun onResume() {
         super.onResume()
         navController.addOnDestinationChangedListener(listener)
     }
     override fun onPause() {
         navController.removeOnDestinationChangedListener(listener)
         super.onPause()
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
     private val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
         Log.w(TAG, " destination changed: $destination" )
         if(destination.id == R.id.listFragment){
             Log.w(TAG, "hide back button " )

             //supportActionBar?.nav(null)
         }else {
             Log.w(TAG, "show back button " )
             //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
         }
     }
}