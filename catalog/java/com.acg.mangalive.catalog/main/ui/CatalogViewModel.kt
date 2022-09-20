package com.acg.mangalive.catalog.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.acg.mangalive.catalog.R
import com.acg.mangalive.catalog.domain.model.SortingCriterion
import com.acg.mangalive.catalog.domain.model.SortingParameters
import com.acg.mangalive.catalog.domain.useCases.MangaCatalogUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

val DEFAULT_SORTING_CRITERION = SortingCriterion.popularity
val DEFAULT_SORTING_CRITERION_VALUE = R.string.sortingMenu_popularity

class CatalogViewModel @AssistedInject constructor(
    @Assisted val savedStateHandle: SavedStateHandle,
    private val mangaCatalogUseCase: MangaCatalogUseCase
) :
    ViewModel() {
    private var _sortingParameters =
        MutableLiveData(SortingParameters(criterion = DEFAULT_SORTING_CRITERION))
    val sortingParameters: LiveData<SortingParameters> = _sortingParameters

    val catalog = sortingParameters.map(::newPager).switchMap { it.liveData }

    private fun newPager(sortingParameters: SortingParameters) =
        Pager(PagingConfig(5)) { mangaCatalogUseCase(sortingParameters) }

    fun setSortingCriterion(value: SortingCriterion) {
        _sortingParameters.value = _sortingParameters.value?.copy(criterion = value)
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): CatalogViewModel
    }
}
