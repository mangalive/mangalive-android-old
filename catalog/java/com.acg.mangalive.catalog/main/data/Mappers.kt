package com.acg.mangalive.catalog.data

import com.acg.mangalive.catalog.data.model.MangaCardDto
import com.acg.mangalive.catalog.domain.model.MangaCard

internal fun MangaCardDto.toMangaCard(): MangaCard =
    MangaCard(
        id = id,
        title = title,
        description = description,
        rating = rating
    )