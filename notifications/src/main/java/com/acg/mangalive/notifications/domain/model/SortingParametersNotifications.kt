package com.acg.mangalive.notifications.domain.model

enum class SortingCriterionNotifications{
    ForToday,
    ThisWeek,
    All,
    Released,
    Answers
}

data class SortingParametersNotifications(val criterion: SortingCriterionNotifications)