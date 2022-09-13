package com.acg.mangalive.notifications.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationsResponseDto(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("cards") val cards: List<NotificationsCardDto>,
)