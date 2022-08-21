package com.acg.mangalive.data

import androidx.paging.PagingSource
import com.acg.mangalive.domain.model.MangaCard
import com.acg.mangalive.data.network.MangaCatalogPagingSource
import com.acg.mangalive.domain.model.SortingParameters
import com.acg.mangalive.domain.repository.MangaCatalogRepository
import javax.inject.Inject

class OnlineMangaCatalogRepository @Inject constructor(private val mangaCatalogPagingSourceFactory: MangaCatalogPagingSource.Factory) :
    MangaCatalogRepository {
    override fun getAllCatalog(sortingParameters: SortingParameters): PagingSource<Long, MangaCard> =
        mangaCatalogPagingSourceFactory.create(sortingParameters)
}