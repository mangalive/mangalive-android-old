package com.acg.mangalive.myManga.di

import com.acg.mangalive.myManga.data.OnlineCatalogRepository
import com.acg.mangalive.myManga.data.network.CatalogService
import com.acg.mangalive.myManga.data.network.FakeCatalogService
import com.acg.mangalive.myManga.domain.repositores.CatalogRepository
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
    fun provideOnlineCatalogRepository(
        onlineCatalogRepository: OnlineCatalogRepository
    ): CatalogRepository
}

@Module
class NetworkModule {
    @Provides
    fun provideCatalogService(json: Json): CatalogService {
//        return buildCatalogService(json)
        return FakeCatalogService()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun buildMangaService(json: Json): CatalogService {
        val httpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(CatalogService::class.java)
    }
}
