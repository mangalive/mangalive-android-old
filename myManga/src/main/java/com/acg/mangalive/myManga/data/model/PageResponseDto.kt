package com.acg.mangalive.myManga.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageResponseDto(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("cards") val cards: List<MangaCardDto>,
)