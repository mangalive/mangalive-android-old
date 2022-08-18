package com.acg.mangalive.data.network.model

import com.acg.mangalive.data.network.model.MangaCardDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaPageResponseDto(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("cards") val cards: List<MangaCardDto>,
)
