package com.acg.mangalive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.acg.mangalive.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
         */

        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.MainActivity_BottomAppBar)
        val navController = findNavController(R.id.MainActivity_NavHostFragment)

        navView.setupWithNavController(navController)
    }


}