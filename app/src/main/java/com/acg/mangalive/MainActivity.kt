package com.acg.mangalive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.acg.mangalive.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import com.acg.mangalive.navigation.R as navR

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        window.allowEnterTransitionOverlap = true

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
                R.id.BottomAppBarMenu_Catalog -> navController.navigate(navR.id.NavGraph_Catalog)
                R.id.BottomAppBarMenu_MyManga -> navController.navigate(navR.id.NavGraph_MyManga)
                R.id.BottomAppBarMenu_News -> navController.navigate(navR.id.NavGraph_News)
            }
            true
        }
    }
}