package com.acg.mangalive.notifications.di

import com.acg.mangalive.notifications.ui.NotificationsBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [DataModule::class, DomainModule::class])
interface NotificationsModule {
    @ContributesAndroidInjector
    fun contributeBottomSheet(): NotificationsBottomSheet
}