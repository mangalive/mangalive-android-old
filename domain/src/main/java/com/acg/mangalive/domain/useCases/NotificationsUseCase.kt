package com.acg.mangalive.domain.useCases

import androidx.paging.PagingSource
import com.acg.mangalive.domain.model.NotificationsCard
import com.acg.mangalive.domain.model.SortingParametersNotifications
import com.acg.mangalive.domain.repository.NotificationsRepository
import javax.inject.Inject

class NotificationsUseCase @Inject constructor(private val notificationsRepository: NotificationsRepository) {
    operator fun invoke(sortingParametersNotifications: SortingParametersNotifications): PagingSource<Long, NotificationsCard> =
        notificationsRepository.getAllCatalog(sortingParametersNotifications)
}