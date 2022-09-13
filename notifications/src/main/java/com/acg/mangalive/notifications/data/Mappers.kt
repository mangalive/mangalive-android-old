package com.acg.mangalive.data

import com.acg.mangalive.notifications.data.model.NotificationsCardDto
import com.acg.mangalive.notifications.domain.model.NotificationsCard


internal fun NotificationsCardDto.toNotificationsCard(): NotificationsCard =
    NotificationsCard(
        id = id,
        title = title,
        chapter = chapter,
        date = date
    )