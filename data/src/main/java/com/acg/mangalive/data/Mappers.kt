package com.acg.mangalive.data

import com.acg.mangalive.domain.model.MangaCard
import com.acg.mangalive.data.network.model.MangaCardDto
import com.acg.mangalive.data.network.model.NotificationsCardDto
import com.acg.mangalive.domain.model.NotificationsCard

internal fun MangaCardDto.toMangaCard(): MangaCard =
    MangaCard(
        id = id,
        title = title,
        description = description,
        rating = rating
    )

internal fun NotificationsCardDto.toNotificationsCard(): NotificationsCard =
    NotificationsCard(
        id = id,
        title = title,
        chapter = chapter,
        date = date
    )