package com.acg.mangalive.di

import android.app.Application
import com.acg.mangalive.MangaliveApp
import com.acg.mangalive.data.di.DataModule
import com.acg.mangalive.domain.di.DomainModule
import com.acg.mangalive.share.ShareModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        ViewModelModule::class,
        DataModule::class,
        DomainModule::class,
        ShareModule::class
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