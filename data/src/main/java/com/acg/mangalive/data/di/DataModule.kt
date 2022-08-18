package com.acg.mangalive.data.di

import com.acg.mangalive.data.OnlineMangaCatalogRepository
import com.acg.mangalive.data.network.di.NetworkModule
import com.acg.mangalive.domain.repository.MangaCatalogRepository
import dagger.Module

@Module(includes = [NetworkModule::class])
interface DataModule {
    fun provideOnlineMangaCatalogRepository(onlineMangaCatalogRepository: OnlineMangaCatalogRepository): MangaCatalogRepository
}