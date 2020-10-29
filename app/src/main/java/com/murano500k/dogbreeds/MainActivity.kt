 package com.murano500k.dogbreeds

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.murano500k.dogbreeds.base.DataBindingActivity
import com.murano500k.dogbreeds.databinding.ActivityMainBinding
import com.murano500k.dogbreeds.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


 @AndroidEntryPoint
class MainActivity : DataBindingActivity() {
     @VisibleForTesting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController*/
    }
}