package com.acg.mangalive.data.di

import com.acg.mangalive.data.OnlineMangaCatalogRepository
import com.acg.mangalive.data.OnlineNotificationsRepository
import com.acg.mangalive.data.network.di.NetworkModule
import com.acg.mangalive.domain.repository.MangaCatalogRepository
import com.acg.mangalive.domain.repository.NotificationsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
interface DataModule {
    @Binds
    fun provideOnlineMangaCatalogRepository(
        onlineMangaCatalogRepository: OnlineMangaCatalogRepository
    ): MangaCatalogRepository

    @Binds
    fun provideOnlineNotificationsRepository(
        onlineNotificationsRepository: OnlineNotificationsRepository
    ): NotificationsRepository
}