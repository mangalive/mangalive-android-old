package com.acg.mangalive.data.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB#\b\u0007\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ*\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\r\u001a\u00020\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J+\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J#\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0017H\u0016\u00a2\u0006\u0002\u0010\u0018J+\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/acg/mangalive/data/network/NotificationsPagingSource;", "Landroidx/paging/PagingSource;", "", "Lcom/acg/mangalive/domain/model/NotificationsCard;", "sortingParametersNotifications", "Lcom/acg/mangalive/domain/model/SortingParametersNotifications;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "notificationsService", "Lcom/acg/mangalive/data/network/NotificationsService;", "(Lcom/acg/mangalive/domain/model/SortingParametersNotifications;Lkotlinx/coroutines/CoroutineDispatcher;Lcom/acg/mangalive/data/network/NotificationsService;)V", "checkResponse", "Landroidx/paging/PagingSource$LoadResult;", "pageNumber", "response", "Lretrofit2/Response;", "Lcom/acg/mangalive/data/network/model/NotificationsResponseDto;", "doResponse", "params", "Landroidx/paging/PagingSource$LoadParams;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRefreshKey", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/PagingState;)Ljava/lang/Long;", "load", "Factory", "data_debug"})
public final class NotificationsPagingSource extends androidx.paging.PagingSource<java.lang.Long, com.acg.mangalive.domain.model.NotificationsCard> {
    private final com.acg.mangalive.domain.model.SortingParametersNotifications sortingParametersNotifications = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final com.acg.mangalive.data.network.NotificationsService notificationsService = null;
    
    @dagger.assisted.AssistedInject()
    public NotificationsPagingSource(@org.jetbrains.annotations.NotNull()
    @dagger.assisted.Assisted(value = "sortingParametersNotifications")
    com.acg.mangalive.domain.model.SortingParametersNotifications sortingParametersNotifications, @org.jetbrains.annotations.NotNull()
    @com.acg.mangalive.share.IoDispatcher()
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    com.acg.mangalive.data.network.NotificationsService notificationsService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object load(@org.jetbrains.annotations.NotNull()
    androidx.paging.PagingSource.LoadParams<java.lang.Long> params, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<java.lang.Long, com.acg.mangalive.domain.model.NotificationsCard>> continuation) {
        return null;
    }
    
    private final java.lang.Object doResponse(androidx.paging.PagingSource.LoadParams<java.lang.Long> params, kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<java.lang.Long, com.acg.mangalive.domain.model.NotificationsCard>> continuation) {
        return null;
    }
    
    private final androidx.paging.PagingSource.LoadResult<java.lang.Long, com.acg.mangalive.domain.model.NotificationsCard> checkResponse(long pageNumber, retrofit2.Response<com.acg.mangalive.data.network.model.NotificationsResponseDto> response) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Long getRefreshKey(@org.jetbrains.annotations.NotNull()
    androidx.paging.PagingState<java.lang.Long, com.acg.mangalive.domain.model.NotificationsCard> state) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/acg/mangalive/data/network/NotificationsPagingSource$Factory;", "", "create", "Lcom/acg/mangalive/data/network/NotificationsPagingSource;", "sortingParametersNotifications", "Lcom/acg/mangalive/domain/model/SortingParametersNotifications;", "data_debug"})
    @dagger.assisted.AssistedFactory()
    public static abstract interface Factory {
        
        @org.jetbrains.annotations.NotNull()
        public abstract com.acg.mangalive.data.network.NotificationsPagingSource create(@org.jetbrains.annotations.NotNull()
        @dagger.assisted.Assisted(value = "sortingParametersNotifications")
        com.acg.mangalive.domain.model.SortingParametersNotifications sortingParametersNotifications);
    }
}