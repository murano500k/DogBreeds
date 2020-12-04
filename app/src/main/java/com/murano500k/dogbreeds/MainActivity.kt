 package com.murano500k.dogbreeds

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.murano500k.dogbreeds.fragment.BreedImagesFragment
import com.murano500k.dogbreeds.model.DogBreed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


 @AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
     private lateinit var navController: NavController


     @VisibleForTesting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
         navController.addOnDestinationChangedListener(this)
         val appBarConfiguration = AppBarConfiguration(setOf(R.id.breedListFragment))
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
         if(destination.id == R.id.breedListFragment){
             Log.w(TAG, "hide back button " )

             //supportActionBar?.nav(null)
         }else {
             Log.w(TAG, "show back button " )
             //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
         }
     }

     override fun onDestinationChanged(
         controller: NavController,
         destination: NavDestination,
         arguments: Bundle?
     ) {
         title = when(destination.id) {
             R.id.breedListFragment -> {
                 getString(R.string.allbreeds)
             }
             R.id.breedImagesFragment -> {
                 val dogBreed: DogBreed? =
                     arguments?.getParcelable(BreedImagesFragment.ARG_DOG_BREED)
                 dogBreed.toString()
             }
             R.id.singleImageFragment -> {
                 val dogBreed: DogBreed? =
                     arguments?.getParcelable(BreedImagesFragment.ARG_DOG_BREED)
                 Log.w(TAG, "title="+dogBreed?.imageUrl)
                 dogBreed?.getImageName()
             }

             else -> getString(R.string.app_name)
         }

     }
 }