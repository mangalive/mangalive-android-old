package com.acg.mangalive.data.network.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationsCardDto (
    @SerialName("id") val id: Long = 0,
    @SerialName("title") val title: String = "",
    @SerialName("chapter") val chapter: Int = 0,
    @SerialName("date") val date: Float = 0f,

    )