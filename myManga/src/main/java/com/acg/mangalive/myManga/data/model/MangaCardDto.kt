package com.acg.mangalive.myManga.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaCardDto (
    @SerialName("id") val id: Long = 0,
    @SerialName("title") val title: String = "",
)