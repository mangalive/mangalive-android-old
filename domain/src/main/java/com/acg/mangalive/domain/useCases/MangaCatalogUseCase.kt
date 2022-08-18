package com.acg.mangalive.domain.useCases

import androidx.paging.PagingSource
import com.acg.mangalive.domain.model.MangaCard
import com.acg.mangalive.domain.model.SortingParameters
import com.acg.mangalive.domain.repository.MangaCatalogRepository
import javax.inject.Inject

class MangaCatalogUseCase @Inject constructor(private val mangaCatalogRepository: MangaCatalogRepository) {
    operator fun invoke(sortingParameters: SortingParameters): PagingSource<Long, MangaCard> =
        mangaCatalogRepository.getAllCatalog(sortingParameters)
}