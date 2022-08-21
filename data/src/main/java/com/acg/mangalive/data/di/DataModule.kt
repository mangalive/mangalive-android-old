package com.acg.mangalive.data.di

import com.acg.mangalive.data.OnlineMangaCatalogRepository
import com.acg.mangalive.data.network.di.NetworkModule
import com.acg.mangalive.domain.repository.MangaCatalogRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
interface DataModule {
    @Binds
    fun provideOnlineMangaCatalogRepository(
        onlineMangaCatalogRepository: OnlineMangaCatalogRepository
    ): MangaCatalogRepository
}