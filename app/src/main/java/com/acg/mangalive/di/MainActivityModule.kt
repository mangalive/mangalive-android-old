package com.acg.mangalive.di

import com.acg.mangalive.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeMainActivity(): MainActivity
}