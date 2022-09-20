package com.acg.mangalive.catalog.data

import androidx.paging.PagingSource
import com.acg.mangalive.catalog.data.network.MangaCatalogPagingSource
import com.acg.mangalive.catalog.domain.model.MangaCard
import com.acg.mangalive.catalog.domain.model.SortingParameters
import com.acg.mangalive.catalog.domain.repositories.MangaCatalogRepository
import javax.inject.Inject

class OnlineMangaCatalogRepository @Inject constructor(private val mangaCatalogPagingSourceFactory: MangaCatalogPagingSource.Factory) :
    MangaCatalogRepository {
    override fun getAllCatalog(sortingParameters: SortingParameters): PagingSource<Long, MangaCard> =
        mangaCatalogPagingSourceFactory.create(sortingParameters)
}