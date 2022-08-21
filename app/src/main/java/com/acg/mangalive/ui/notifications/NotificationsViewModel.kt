package com.acg.mangalive.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class NotificationsMenuState{
    ForToday,
    ThisWeek,
    All,
    Released,
    Answers
}

val DEFAULT_NOTIFICATIONS_MENU_STATE = NotificationsMenuState.All

data class NotificationsUiState(
    val notificationsMenuState: NotificationsMenuState = DEFAULT_NOTIFICATIONS_MENU_STATE
)

class NotificationsViewModel : ViewModel() {

    private var _uiState = MutableLiveData(NotificationsUiState())
    val uiState: LiveData<NotificationsUiState> = _uiState

    fun setNotificationsMenuState(value: NotificationsMenuState) {
        _uiState.value = _uiState.value?.copy(notificationsMenuState = value)
    }
}
