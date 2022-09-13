package com.acg.mangalive.share.di

import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module(includes = [CoroutinesModule::class])
class ShareModule {
    @Singleton
    @Provides
    fun provideJson(): Json {
        return Json(Json.Default) {
            ignoreUnknownKeys = true
        }
    }
}