package com.acg.mangalive.myManga.data

import androidx.paging.PagingSource
import com.acg.mangalive.myManga.data.model.MangaCard
import com.acg.mangalive.myManga.data.network.CatalogPagingSource
import com.acg.mangalive.myManga.domain.model.SortingParameters
import com.acg.mangalive.myManga.domain.repositores.CatalogRepository
import javax.inject.Inject

class OnlineCatalogRepository @Inject constructor(private val catalogPagingSourceFactory: CatalogPagingSource.Factory) :
    CatalogRepository {
    override fun getAllCatalog(sortingParameters: SortingParameters): PagingSource<Long, MangaCard> =
        catalogPagingSourceFactory.create(sortingParameters)
}