package com.acg.mangalive.notifications.data

import androidx.paging.PagingSource
import com.acg.mangalive.notifications.data.network.NotificationsPagingSource
import com.acg.mangalive.notifications.domain.model.NotificationsCard
import com.acg.mangalive.notifications.domain.model.SortingParametersNotifications
import com.acg.mangalive.notifications.domain.repositories.NotificationsRepository
import javax.inject.Inject

class OnlineNotificationsRepository @Inject constructor(private val notificationsPagingSourceFactory: NotificationsPagingSource.Factory) :
    NotificationsRepository {
    override fun getAllCatalog(sortingParametersNotifications: SortingParametersNotifications): PagingSource<Long, NotificationsCard> =
        notificationsPagingSourceFactory.create(sortingParametersNotifications)
}