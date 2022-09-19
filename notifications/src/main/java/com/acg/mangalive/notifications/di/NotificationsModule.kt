package com.acg.mangalive.notifications.di

import com.acg.mangalive.notifications.ui.NotificationsBottomSheet
import com.acg.mangalive.notifications.ui.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DataModule::class, DomainModule::class])
interface NotificationsModule {
    @ContributesAndroidInjector
    fun contributeNotificationsModule(): NotificationsFragment

    @ContributesAndroidInjector
    fun contributeBottomSheet(): NotificationsBottomSheet
}