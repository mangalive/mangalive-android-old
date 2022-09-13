package com.acg.mangalive.di

import android.app.Application
import com.acg.mangalive.MangaliveApp
import com.acg.mangalive.share.di.ShareModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ShareModule::class,
        AndroidInjectionModule::class,
        MainActivityModule::class,
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder


        fun build(): AppComponent
    }

    fun inject(app: MangaliveApp)
}