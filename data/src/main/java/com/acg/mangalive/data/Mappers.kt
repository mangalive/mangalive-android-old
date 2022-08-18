package com.acg.mangalive.data

import com.acg.mangalive.domain.model.MangaCard
import com.acg.mangalive.data.network.model.MangaCardDto

internal fun MangaCardDto.toMangaCard(): MangaCard =
    MangaCard(
        id = id,
        title = title,
        description = description,
        rating = rating
    )