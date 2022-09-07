package com.acg.mangalive.viewModel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.acg.mangalive.domain.model.*
import com.acg.mangalive.domain.useCases.NotificationsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

val DEFAULT_SORTING_CRITERION_NOTIFICATIONS = SortingCriterionNotifications.ForToday

class NotificationsViewModel @Inject constructor(private val notificationsUseCase: NotificationsUseCase) :
    ViewModel() {
    private var _sortingParameters = MutableLiveData(SortingParametersNotifications(criterion = DEFAULT_SORTING_CRITERION_NOTIFICATIONS))
    val sortingParameters: LiveData<SortingParametersNotifications> = _sortingParameters

    val notifications = sortingParameters.map(::newPager).switchMap { it.liveData }

    private fun newPager(sortingParametersNotifications: SortingParametersNotifications) =
        Pager(PagingConfig(5)) { notificationsUseCase(sortingParametersNotifications) }

    fun setSortingCriterion(value: SortingCriterionNotifications) {
        _sortingParameters.value = _sortingParameters.value?.copy(criterion = value)
    }
}
