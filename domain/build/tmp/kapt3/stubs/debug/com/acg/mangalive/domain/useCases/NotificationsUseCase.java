package com.acg.mangalive.domain.useCases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\t\u001a\u00020\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/acg/mangalive/domain/useCases/NotificationsUseCase;", "", "notificationsRepository", "Lcom/acg/mangalive/domain/repository/NotificationsRepository;", "(Lcom/acg/mangalive/domain/repository/NotificationsRepository;)V", "invoke", "Landroidx/paging/PagingSource;", "", "Lcom/acg/mangalive/domain/model/NotificationsCard;", "sortingParametersNotifications", "Lcom/acg/mangalive/domain/model/SortingParametersNotifications;", "domain_debug"})
public final class NotificationsUseCase {
    private final com.acg.mangalive.domain.repository.NotificationsRepository notificationsRepository = null;
    
    @javax.inject.Inject()
    public NotificationsUseCase(@org.jetbrains.annotations.NotNull()
    com.acg.mangalive.domain.repository.NotificationsRepository notificationsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.PagingSource<java.lang.Long, com.acg.mangalive.domain.model.NotificationsCard> invoke(@org.jetbrains.annotations.NotNull()
    com.acg.mangalive.domain.model.SortingParametersNotifications sortingParametersNotifications) {
        return null;
    }
}