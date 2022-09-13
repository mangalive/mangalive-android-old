package com.acg.mangalive.catalog.di

import com.acg.mangalive.catalog.data.OnlineMangaCatalogRepository
import com.acg.mangalive.catalog.data.network.FakeMangaService
import com.acg.mangalive.catalog.data.network.MangaService
import com.acg.mangalive.catalog.domain.repositories.MangaCatalogRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
interface DataModule {
    @Binds
    fun provideOnlineMangaCatalogRepository(
        onlineMangaCatalogRepository: OnlineMangaCatalogRepository
    ): MangaCatalogRepository
}

@Module
class NetworkModule {
    @Provides
    fun provideMangaService(json: Json): MangaService {
//        return buildMangaService(json)
        return FakeMangaService()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun buildMangaService(json: Json): MangaService {
        val httpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(MangaService::class.java)
    }
}