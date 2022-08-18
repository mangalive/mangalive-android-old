package com.acg.mangalive.domain.repository

import androidx.paging.PagingSource
import com.acg.mangalive.domain.model.MangaCard
import com.acg.mangalive.domain.model.SortingParameters

interface MangaCatalogRepository {
    fun getAllCatalog(sortingParameters: SortingParameters): PagingSource<Long, MangaCard>
}