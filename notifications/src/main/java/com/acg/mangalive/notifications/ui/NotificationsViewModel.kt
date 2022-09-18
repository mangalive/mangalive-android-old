package com.acg.mangalive.notifications.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.acg.mangalive.notifications.domain.model.SortingCriterionNotifications
import com.acg.mangalive.notifications.domain.model.SortingParametersNotifications
import com.acg.mangalive.notifications.domain.useCases.NotificationsUseCase
import com.google.android.material.chip.Chip
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

val DEFAULT_SORTING_CRITERION_NOTIFICATIONS = SortingCriterionNotifications.ForToday

class NotificationsViewModel @AssistedInject constructor(
    @Assisted val savedStateHandle: SavedStateHandle,
    private val notificationsUseCase: NotificationsUseCase
) :
    ViewModel() {
    private var _sortingParameters =
        MutableLiveData(SortingParametersNotifications(criterion = DEFAULT_SORTING_CRITERION_NOTIFICATIONS))
    val sortingParameters: LiveData<SortingParametersNotifications> = _sortingParameters

    val notifications = sortingParameters.map(::newPager).switchMap { it.liveData }

    private fun newPager(sortingParametersNotifications: SortingParametersNotifications) =
        Pager(PagingConfig(5)) { notificationsUseCase(sortingParametersNotifications) }

    fun setSortingCriterion(value: SortingCriterionNotifications) {
        _sortingParameters.value = _sortingParameters.value?.copy(criterion = value)
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): NotificationsViewModel
    }
}
