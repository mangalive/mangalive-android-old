package com.acg.mangalive.data

import androidx.paging.PagingSource
import com.acg.mangalive.data.network.NotificationsPagingSource
import com.acg.mangalive.domain.model.NotificationsCard
import com.acg.mangalive.domain.model.SortingParametersNotifications
import com.acg.mangalive.domain.repository.NotificationsRepository
import javax.inject.Inject

class OnlineNotificationsRepository @Inject constructor(private val notificationsPagingSourceFactory: NotificationsPagingSource.Factory) :
    NotificationsRepository {
    override fun getAllCatalog(sortingParametersNotifications: SortingParametersNotifications): PagingSource<Long, NotificationsCard> =
        notificationsPagingSourceFactory.create(sortingParametersNotifications)
}