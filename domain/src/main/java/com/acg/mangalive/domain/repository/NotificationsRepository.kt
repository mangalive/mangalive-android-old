package com.acg.mangalive.domain.repository

import androidx.paging.PagingSource
import com.acg.mangalive.domain.model.NotificationsCard
import com.acg.mangalive.domain.model.SortingParametersNotifications

interface NotificationsRepository {
    fun getAllCatalog(sortingParametersNotifications: SortingParametersNotifications): PagingSource<Long, NotificationsCard>
}