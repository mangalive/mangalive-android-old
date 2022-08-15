package com.acg.mangalive

import android.app.Application
import android.content.Context
import com.acg.mangalive.di.AppComponent

class MangaliveApplication : Application() {

    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.Builder()
            .application(this)
            .create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MangaliveApplication -> appComponent
        else -> (applicationContext as MangaliveApplication).appComponent
    }