package com.acg.mangalive.notifications.domain.useCases

import androidx.paging.PagingSource
import com.acg.mangalive.notifications.domain.model.NotificationsCard
import com.acg.mangalive.notifications.domain.model.SortingParametersNotifications
import com.acg.mangalive.notifications.domain.repositories.NotificationsRepository
import javax.inject.Inject

class NotificationsUseCase @Inject constructor(private val notificationsRepository: NotificationsRepository) {
    operator fun invoke(sortingParametersNotifications: SortingParametersNotifications): PagingSource<Long, NotificationsCard> =
        notificationsRepository.getAllCatalog(sortingParametersNotifications)
}