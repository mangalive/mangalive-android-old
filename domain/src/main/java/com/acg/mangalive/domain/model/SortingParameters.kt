package com.acg.mangalive.domain.model

enum class SortingDirection {
    byAscending,
    byDescending,
}

enum class SortingCriterion {
    popularity,
    views,
    chapterCount,
    novelty,
    recentUpdate,
//    likes,
//    random,
}

data class SortingParameters(val direction: SortingDirection, val criterion: SortingCriterion)