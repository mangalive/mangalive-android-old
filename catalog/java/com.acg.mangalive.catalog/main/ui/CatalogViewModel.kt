package com.acg.mangalive.catalog.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.acg.mangalive.catalog.domain.model.*
import com.acg.mangalive.catalog.domain.useCases.MangaCatalogUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CatalogViewModel @AssistedInject constructor(
    @Assisted val savedStateHandle: SavedStateHandle,
    private val mangaCatalogUseCase: MangaCatalogUseCase
) :
    ViewModel() {
    private val _selectedFilters = MutableLiveData(SelectedFilters())
    val catalog = _selectedFilters.map(::newPager).switchMap { it.liveData }

    private fun newPager(selectedFilters: SelectedFilters) =
        Pager(PagingConfig(5)) { mangaCatalogUseCase(selectedFilters) }


    fun setSortingCriterion(value: SortingCriterionFilter) {
        _selectedFilters.value = _selectedFilters.value?.copy(sortingCriterion = value)
    }

    fun setTypes(value: List<TypeFilter>) {
        _selectedFilters.value = _selectedFilters.value?.copy(types = value)
    }

    fun setGenres(value: List<GenreFilter>) {
        _selectedFilters.value = _selectedFilters.value?.copy(genres = value)
    }

    fun setCategories(value: List<CategoryFilter>) {
        _selectedFilters.value = _selectedFilters.value?.copy(categories = value)
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): CatalogViewModel
    }
}
