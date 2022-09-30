package com.acg.mangalive.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/acg/mangalive/data/OnlineMangaCatalogRepository;", "Lcom/acg/mangalive/domain/repository/MangaCatalogRepository;", "mangaCatalogPagingSourceFactory", "Lcom/acg/mangalive/data/network/MangaCatalogPagingSource$Factory;", "(Lcom/acg/mangalive/data/network/MangaCatalogPagingSource$Factory;)V", "getAllCatalog", "Landroidx/paging/PagingSource;", "", "Lcom/acg/mangalive/domain/model/MangaCard;", "sortingParameters", "Lcom/acg/mangalive/domain/model/SortingParameters;", "data_debug"})
public final class OnlineMangaCatalogRepository implements com.acg.mangalive.domain.repository.MangaCatalogRepository {
    private final com.acg.mangalive.data.network.MangaCatalogPagingSource.Factory mangaCatalogPagingSourceFactory = null;
    
    @javax.inject.Inject()
    public OnlineMangaCatalogRepository(@org.jetbrains.annotations.NotNull()
    com.acg.mangalive.data.network.MangaCatalogPagingSource.Factory mangaCatalogPagingSourceFactory) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.paging.PagingSource<java.lang.Long, com.acg.mangalive.domain.model.MangaCard> getAllCatalog(@org.jetbrains.annotations.NotNull()
    com.acg.mangalive.domain.model.SortingParameters sortingParameters) {
        return null;
    }
}