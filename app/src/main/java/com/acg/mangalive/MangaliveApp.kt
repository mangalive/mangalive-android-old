package com.acg.mangalive

import android.app.Application
import android.content.Context
import com.acg.mangalive.di.AppComponent
import com.acg.mangalive.di.DaggerAppComponent

class MangaliveApp : Application() {
    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()

        _appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MangaliveApp -> appComponent
        else -> applicationContext.appComponent
    }