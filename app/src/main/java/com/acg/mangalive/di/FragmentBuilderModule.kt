package com.acg.mangalive.di

import com.acg.mangalive.ui.catalog.CatalogFragment
import com.acg.mangalive.ui.favourites.FavouritesFragment
import com.acg.mangalive.ui.news.NewsFragment
import com.acg.mangalive.ui.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {
    @ContributesAndroidInjector
    fun contributeCatalogFragment(): CatalogFragment

    @ContributesAndroidInjector
    fun contributeFavouritesFragment(): FavouritesFragment

    @ContributesAndroidInjector
    fun contributeNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    fun contributeNotificationsFragment(): NotificationsFragment
}