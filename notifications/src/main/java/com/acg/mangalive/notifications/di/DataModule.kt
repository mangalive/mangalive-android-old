package com.acg.mangalive.notifications.di

import com.acg.mangalive.notifications.data.OnlineNotificationsRepository
import com.acg.mangalive.notifications.data.network.FakeNotificationsService
import com.acg.mangalive.notifications.data.network.NotificationsService
import com.acg.mangalive.notifications.domain.repositories.NotificationsRepository
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
    fun provideOnlineNotificationsRepository(
        onlineNotificationsRepository: OnlineNotificationsRepository
    ): NotificationsRepository
}

@Module
class NetworkModule {
    @Provides
    fun provideNotificationsService(json: Json): NotificationsService {
//        return buildNotificationsService(json)
        return FakeNotificationsService()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun bildNotificationsService(json: Json): NotificationsService {
        val httpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(NotificationsService::class.java)
    }
}