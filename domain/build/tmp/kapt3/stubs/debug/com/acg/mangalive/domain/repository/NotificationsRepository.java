package com.acg.mangalive.domain.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/acg/mangalive/domain/repository/NotificationsRepository;", "", "getAllCatalog", "Landroidx/paging/PagingSource;", "", "Lcom/acg/mangalive/domain/model/NotificationsCard;", "sortingParametersNotifications", "Lcom/acg/mangalive/domain/model/SortingParametersNotifications;", "domain_debug"})
public abstract interface NotificationsRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.paging.PagingSource<java.lang.Long, com.acg.mangalive.domain.model.NotificationsCard> getAllCatalog(@org.jetbrains.annotations.NotNull()
    com.acg.mangalive.domain.model.SortingParametersNotifications sortingParametersNotifications);
}