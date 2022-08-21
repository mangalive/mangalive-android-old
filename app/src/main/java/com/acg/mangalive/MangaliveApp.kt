package com.acg.mangalive

import android.app.Activity
import android.app.Application
import android.content.Context
import com.acg.mangalive.di.AppComponent
import com.acg.mangalive.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MangaliveApp : Application(), HasAndroidInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}