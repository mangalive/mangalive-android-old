package com.acg.mangalive.myManga.data

import com.acg.mangalive.myManga.data.model.MangaCard
import com.acg.mangalive.myManga.data.model.MangaCardDto

internal fun MangaCardDto.toMangaCard(): MangaCard =
    MangaCard(
        id = id,
        title = title,
    )