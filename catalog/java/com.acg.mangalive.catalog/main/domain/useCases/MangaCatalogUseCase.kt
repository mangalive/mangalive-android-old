package com.acg.mangalive.catalog.domain.useCases

import androidx.paging.PagingSource
import com.acg.mangalive.catalog.domain.model.MangaCard
import com.acg.mangalive.catalog.domain.model.SelectedFilters
import com.acg.mangalive.catalog.domain.repositories.MangaCatalogRepository
import javax.inject.Inject

class MangaCatalogUseCase @Inject constructor(private val mangaCatalogRepository: MangaCatalogRepository) {
    operator fun invoke(selectedFilters: SelectedFilters): PagingSource<Long, MangaCard> =
        mangaCatalogRepository.getAllCatalog(selectedFilters)
}