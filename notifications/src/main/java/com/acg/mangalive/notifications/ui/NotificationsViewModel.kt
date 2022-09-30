package com.acg.mangalive.notifications.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.acg.mangalive.notifications.domain.model.SortingCriterionNotifications
import com.acg.mangalive.notifications.domain.model.SortingParametersNotifications
import com.acg.mangalive.notifications.domain.useCases.NotificationsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Singleton

val DEFAULT_SORTING_CRITERION_NOTIFICATIONS = SortingCriterionNotifications.ForToday

class NotificationsViewModel @AssistedInject constructor(
    @Assisted val savedStateHandle: SavedStateHandle,
    private val notificationsUseCase: NotificationsUseCase
) :
    ViewModel() {
    private var _sortingParameters =
        MutableLiveData(DEFAULT_SORTING_CRITERION_NOTIFICATIONS)
    val sortingParameters: LiveData<SortingCriterionNotifications> = _sortingParameters

    val notifications = sortingParameters.map(::newPager).switchMap { it.liveData }

    init {
        Log.i("gjdsogjdfspo", "saougfsiygio")
    }

    private fun newPager(sortingCriterionNotifications: SortingCriterionNotifications) =
        Pager(PagingConfig(5)) { notificationsUseCase(SortingParametersNotifications(sortingCriterionNotifications)) }

    fun updateSortingParameters(value: SortingCriterionNotifications) {
        _sortingParameters.value = value
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): NotificationsViewModel
    }
}
