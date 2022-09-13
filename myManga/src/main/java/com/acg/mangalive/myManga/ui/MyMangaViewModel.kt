package com.acg.mangalive.myManga.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.acg.mangalive.myManga.domain.model.SortingCriterion
import com.acg.mangalive.myManga.domain.model.SortingParameters
import com.acg.mangalive.myManga.domain.useCases.CatalogUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

val DEFAULT_SORTING_PARAMETERS_STATE = SortingParameters(SortingCriterion.CurrentlyReading)

data class UiState(
    val favouritesMenuState: SortingParameters = DEFAULT_SORTING_PARAMETERS_STATE
)

class MyMangaViewModel @AssistedInject constructor(
    @Assisted val savedStateHandle: SavedStateHandle,
    private val CatalogUseCase: CatalogUseCase
    ) :
    ViewModel() {

    private var _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    val favourites = uiState.map(::newPager).switchMap { it.liveData }

    private fun newPager(uiState: UiState) =
        Pager(PagingConfig(5)) { CatalogUseCase(uiState.favouritesMenuState) }

    fun setCategoryMenuState(value: SortingParameters) {
        _uiState.value = _uiState.value?.copy(favouritesMenuState = value)
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): MyMangaViewModel
    }
}