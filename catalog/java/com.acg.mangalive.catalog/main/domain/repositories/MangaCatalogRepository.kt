package com.acg.mangalive.catalog.domain.repositories

import androidx.paging.PagingSource
import com.acg.mangalive.catalog.domain.model.MangaCard
import com.acg.mangalive.catalog.domain.model.SelectedFilters

interface MangaCatalogRepository {
    fun getAllCatalog(selectedFilters: SelectedFilters): PagingSource<Long, MangaCard>
}