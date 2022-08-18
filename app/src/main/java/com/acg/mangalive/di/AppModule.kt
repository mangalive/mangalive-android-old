package com.acg.mangalive.di

import com.acg.mangalive.data.di.DataModule
import com.acg.mangalive.domain.di.DomainModule
import com.acg.mangalive.share.ShareModule
import dagger.Module

@Module(includes = [DataModule::class, DomainModule::class, ShareModule::class])
class AppModule