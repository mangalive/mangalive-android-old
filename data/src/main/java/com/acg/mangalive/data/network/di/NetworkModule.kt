package com.acg.mangalive.data.network.di

import com.acg.mangalive.data.network.FakeMangaService
import com.acg.mangalive.data.network.MangaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json(Json.Default) {
            ignoreUnknownKeys = true
        }
    }

    @Singleton
    @Provides
    fun provideMangaService(json: Json): MangaService {
//        return buildMangaService(json)
        return FakeMangaService()
    }
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