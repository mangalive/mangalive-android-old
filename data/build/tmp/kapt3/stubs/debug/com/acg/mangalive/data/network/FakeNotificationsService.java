package com.acg.mangalive.data.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/acg/mangalive/data/network/FakeNotificationsService;", "Lcom/acg/mangalive/data/network/NotificationsService;", "()V", "getPage", "Lretrofit2/Response;", "Lcom/acg/mangalive/data/network/model/NotificationsResponseDto;", "pageNumber", "", "pageSize", "sortingCriterionNotifications", "Lcom/acg/mangalive/domain/model/SortingCriterionNotifications;", "(JJLcom/acg/mangalive/domain/model/SortingCriterionNotifications;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class FakeNotificationsService implements com.acg.mangalive.data.network.NotificationsService {
    
    public FakeNotificationsService() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getPage(long pageNumber, long pageSize, @org.jetbrains.annotations.NotNull()
    com.acg.mangalive.domain.model.SortingCriterionNotifications sortingCriterionNotifications, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.acg.mangalive.data.network.model.NotificationsResponseDto>> continuation) {
        return null;
    }
}