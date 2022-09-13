package com.acg.mangalive.catalog.domain.repositories

import androidx.paging.PagingSource
import com.acg.mangalive.catalog.domain.model.MangaCard
import com.acg.mangalive.catalog.domain.model.SortingParameters

interface MangaCatalogRepository {
    fun getAllCatalog(sortingParameters: SortingParameters): PagingSource<Long, MangaCard>
}