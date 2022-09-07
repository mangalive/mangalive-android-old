package com.acg.mangalive.domain.model

enum class SortingCriterionNotifications{
    ForToday,
    ThisWeek,
    All,
    Released,
    Answers
}

data class SortingParametersNotifications(val criterion: SortingCriterionNotifications)