package com.acg.mangalive.di

import com.acg.mangalive.MainActivity
import com.acg.mangalive.catalog.di.CatalogModule
import com.acg.mangalive.catalog.ui.CatalogFragment
import com.acg.mangalive.myManga.di.MyMangaModule
import com.acg.mangalive.myManga.ui.MyMangaFragment
import com.acg.mangalive.news.ui.NewsFragment
import com.acg.mangalive.news.ui.di.NewsModule
import com.acg.mangalive.notifications.di.NotificationsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [NotificationsModule::class])
interface MainActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [CatalogModule::class])
    fun contributeCatalogFragment(): CatalogFragment

    @ContributesAndroidInjector(modules = [MyMangaModule::class])
    fun contributeMyMangaModule(): MyMangaFragment

    @ContributesAndroidInjector(modules = [NewsModule::class])
    fun contributeNewsModule(): NewsFragment
}