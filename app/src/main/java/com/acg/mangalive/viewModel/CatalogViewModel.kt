package com.acg.mangalive.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class SortingMenuState {
    Popularity,
    Likes,
    Views,
    ChapterCount,
    Novelty,
    RecentUpdate,
    Random,
}

val DEFAULT_SORTING_MENU_STATE = SortingMenuState.Popularity

data class CatalogUiState(
    val sortingMenuState: SortingMenuState = DEFAULT_SORTING_MENU_STATE
)

class CatalogViewModel : ViewModel() {

    private var _uiState = MutableLiveData(CatalogUiState())
    val uiState: LiveData<CatalogUiState> = _uiState

    fun setSortingMenuState(value: SortingMenuState) {
        _uiState.value = _uiState.value?.copy(sortingMenuState = value)
    }
}
