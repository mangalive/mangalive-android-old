package com.acg.mangalive.domain.model

enum class SortingCriterion {
    popularity,
    views,
    chapterCount,
    novelty,
    recentUpdates,
//    likes,
//    random,
}

data class SortingParameters(val criterion: SortingCriterion)