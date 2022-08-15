package com.acg.mangalive.di

import android.app.Application
import dagger.Component
import javax.inject.Singleton
import com.acg.mangalive.ui.MainActivity
import dagger.BindsInstance

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}