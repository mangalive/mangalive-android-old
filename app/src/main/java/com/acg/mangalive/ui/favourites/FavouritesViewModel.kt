package com.acg.mangalive.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

enum class CategoriesMenuState {
    CurrentlyReading,
    PlanToRead,
    Completed,
    OnHold,
    Dropped
}

val DEFAULT_CATEGORY_MENU_STATE = CategoriesMenuState.CurrentlyReading

data class FavouritesUiState(
    val categoriesMenuState: CategoriesMenuState = DEFAULT_CATEGORY_MENU_STATE
)

class FavouritesViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableLiveData(FavouritesUiState())
    val uiState: LiveData<FavouritesUiState> = _uiState

    fun setCategoryMenuState(value: CategoriesMenuState) {
        _uiState.value = _uiState.value?.copy(categoriesMenuState = value)
    }
}
