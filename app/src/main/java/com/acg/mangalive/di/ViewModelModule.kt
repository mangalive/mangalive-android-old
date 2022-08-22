package com.acg.mangalive.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acg.mangalive.MangaliveViewModelFactory
import com.acg.mangalive.viewModel.CatalogViewModel
import com.acg.mangalive.viewModel.FavouritesViewModel
import com.acg.mangalive.viewModel.NotificationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(CatalogViewModel::class)]
    fun bindCatalogViewModel(catalogViewModel: CatalogViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(FavouritesViewModel::class)]
    fun bindFavouritesViewModel(favouritesViewModel: FavouritesViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(NotificationsViewModel::class)]
    fun bindNotificationsViewModel(notificationsViewModel: NotificationsViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: MangaliveViewModelFactory): ViewModelProvider.Factory
}