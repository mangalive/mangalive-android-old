package com.acg.mangalive.myManga.domain.useCases

import androidx.paging.PagingSource
import com.acg.mangalive.myManga.data.model.MangaCard
import com.acg.mangalive.myManga.domain.model.SortingParameters
import com.acg.mangalive.myManga.domain.repositores.CatalogRepository
import javax.inject.Inject

class CatalogUseCase @Inject constructor(private val catalogRepository: CatalogRepository) {
    operator fun invoke(sortingParameters: SortingParameters): PagingSource<Long, MangaCard> =
        catalogRepository.getAllCatalog(sortingParameters)
}