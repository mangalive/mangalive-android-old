package com.acg.mangalive.catalog.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaCardDto (
    @SerialName("id") val id: Long = 0,
    @SerialName("title") val title: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("rating") val rating: Float = 0f,
    @SerialName("chapters") val chapters: Int = 0,
    @SerialName("views") val views: Int = 0,
)