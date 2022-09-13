package com.acg.mangalive.myManga.domain.repositores

import androidx.paging.PagingSource
import com.acg.mangalive.myManga.data.model.MangaCard
import com.acg.mangalive.myManga.domain.model.SortingParameters

interface CatalogRepository {
    fun getAllCatalog(sortingParameters: SortingParameters): PagingSource<Long, MangaCard>
}