package com.acg.mangalive.notifications.di

import dagger.Module

@Module(includes = [DataModule::class, DomainModule::class])
interface NotificationsModule