package com.acg.mangalive.catalog.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaPageResponseDto(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("cards") val cards: List<MangaCardDto>,
)
