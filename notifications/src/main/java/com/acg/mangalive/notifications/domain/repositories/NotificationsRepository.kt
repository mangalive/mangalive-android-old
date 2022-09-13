package com.acg.mangalive.notifications.domain.repositories

import androidx.paging.PagingSource
import com.acg.mangalive.notifications.domain.model.NotificationsCard
import com.acg.mangalive.notifications.domain.model.SortingParametersNotifications

interface NotificationsRepository {
    fun getAllCatalog(sortingParametersNotifications: SortingParametersNotifications): PagingSource<Long, NotificationsCard>
}