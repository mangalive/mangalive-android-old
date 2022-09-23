package com.acg.mangalive.catalog.domain.model

data class MangaCard(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val rating: Float = 0f,
    val views: Int = 0,
    val chapters: Int = 0,
)
