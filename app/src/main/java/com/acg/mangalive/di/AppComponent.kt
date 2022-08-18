package com.acg.mangalive.di

import android.app.Application
import com.acg.mangalive.MangaliveApp
import com.acg.mangalive.ui.MainActivity
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}