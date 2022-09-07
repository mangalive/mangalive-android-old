package com.acg.mangalive.data.network.model

import com.acg.mangalive.domain.model.NotificationsCard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationsResponseDto(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("cards") val cards: List<NotificationsCardDto>,
)