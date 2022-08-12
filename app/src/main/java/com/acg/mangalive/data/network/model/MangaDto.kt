package com.acg.mangalive.data.network.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaDto(
    @SerialName("id") val id: Long = 0,
    @SerialName("title") val title: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("likes") val likes: Long = 0,
    @SerialName("views") val views: Long = 0,
    @SerialName("numberOfChapters") val numberOfChapters: Long = 0,
    @SerialName("realiseDate") val realiseDate: LocalDateTime = LocalDateTime(0, 0, 0, 0, 0)
)