package com.acg.mangalive.data.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\'\u00a8\u0006\n"}, d2 = {"Lcom/acg/mangalive/data/di/DataModule;", "", "provideOnlineMangaCatalogRepository", "Lcom/acg/mangalive/domain/repository/MangaCatalogRepository;", "onlineMangaCatalogRepository", "Lcom/acg/mangalive/data/OnlineMangaCatalogRepository;", "provideOnlineNotificationsRepository", "Lcom/acg/mangalive/domain/repository/NotificationsRepository;", "onlineNotificationsRepository", "Lcom/acg/mangalive/data/OnlineNotificationsRepository;", "data_debug"})
@dagger.Module(includes = {com.acg.mangalive.data.network.di.NetworkModule.class})
public abstract interface DataModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract com.acg.mangalive.domain.repository.MangaCatalogRepository provideOnlineMangaCatalogRepository(@org.jetbrains.annotations.NotNull()
    com.acg.mangalive.data.OnlineMangaCatalogRepository onlineMangaCatalogRepository);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract com.acg.mangalive.domain.repository.NotificationsRepository provideOnlineNotificationsRepository(@org.jetbrains.annotations.NotNull()
    com.acg.mangalive.data.OnlineNotificationsRepository onlineNotificationsRepository);
}