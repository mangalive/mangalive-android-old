package com.acg.mangalive.di

import com.acg.mangalive.MainActivity
import com.acg.mangalive.catalog.di.CatalogModule
import com.acg.mangalive.catalog.ui.CatalogFragment
import com.acg.mangalive.myManga.di.MyMangaModule
import com.acg.mangalive.myManga.ui.MyMangaFragment
import com.acg.mangalive.news.ui.NewsFragment
import com.acg.mangalive.news.ui.di.NewsModule
import com.acg.mangalive.notifications.di.NotificationsModule
import com.acg.mangalive.notifications.ui.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [CatalogModule::class])
    fun contributeCatalogFragment(): CatalogFragment

    @ContributesAndroidInjector(modules = [NotificationsModule::class])
    fun contributeNotificationsModule(): NotificationsFragment

    @ContributesAndroidInjector(modules = [MyMangaModule::class])
    fun contributeMyMangaModule(): MyMangaFragment

    @ContributesAndroidInjector(modules = [NewsModule::class])
    fun contributeNewsModule(): NewsFragment
}