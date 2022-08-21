package com.acg.mangalive.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.acg.mangalive.R
import com.acg.mangalive.appComponent
import com.acg.mangalive.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        this.appComponent.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.MainActivityBottomAppBar
        val navBar = navView as NavigationBarView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.MainActivity_NavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        navView.setupWithNavController(navController)



        navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.BottomAppBarMenu_Catalog -> navController.navigate(R.id.NavGraph_Catalog)
                R.id.BottomAppBarMenu_Favourites -> navController.navigate(R.id.NavGraph_Favourites)
                R.id.BottomAppBarMenu_News -> navController.navigate(R.id.NavGraph_News)
            }
            true
        }
    }
}