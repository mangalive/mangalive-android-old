package com.acg.mangalive.data.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bJ5\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/acg/mangalive/data/network/NotificationsService;", "", "getPage", "Lretrofit2/Response;", "Lcom/acg/mangalive/data/network/model/NotificationsResponseDto;", "pageNumber", "", "pageSize", "sortingCriterionNotifications", "Lcom/acg/mangalive/domain/model/SortingCriterionNotifications;", "(JJLcom/acg/mangalive/domain/model/SortingCriterionNotifications;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "data_debug"})
public abstract interface NotificationsService {
    @org.jetbrains.annotations.NotNull()
    public static final com.acg.mangalive.data.network.NotificationsService.Companion Companion = null;
    public static final long INITIAL_PAGE_NUMBER = 1L;
    public static final long MAX_PAGE_SIZE = 20L;
    public static final long DEFAULT_PAGE_SIZE = 20L;
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "page/{pageNumber}")
    public abstract java.lang.Object getPage(@androidx.annotation.IntRange(from = 1L)
    @retrofit2.http.Path(value = "pageNumber")
    long pageNumber, @androidx.annotation.IntRange(from = 1L, to = 20L)
    @retrofit2.http.Query(value = "pageSize")
    long pageSize, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "sortingCriterion")
    com.acg.mangalive.domain.model.SortingCriterionNotifications sortingCriterionNotifications, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.acg.mangalive.data.network.model.NotificationsResponseDto>> continuation);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/acg/mangalive/data/network/NotificationsService$Companion;", "", "()V", "DEFAULT_PAGE_SIZE", "", "INITIAL_PAGE_NUMBER", "MAX_PAGE_SIZE", "data_debug"})
    public static final class Companion {
        public static final long INITIAL_PAGE_NUMBER = 1L;
        public static final long MAX_PAGE_SIZE = 20L;
        public static final long DEFAULT_PAGE_SIZE = 20L;
        
        private Companion() {
            super();
        }
    }
}