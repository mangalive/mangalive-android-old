package com.acg.mangalive.catalog.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FilterViewModel @AssistedInject constructor(@Assisted savedStateHandle: SavedStateHandle) :
    ViewModel() {
    private var _singleSelectIndex = MutableLiveData<Pair<String, Int>>()
    private var _nonSingleSelectIndexes = MutableLiveData<Pair<String, List<Int>>>()

    val singleSelectIndex: LiveData<Pair<String, Int>> = _singleSelectIndex
    val nonSingleSelectIndexes: LiveData<Pair<String, List<Int>>> = _nonSingleSelectIndexes

    fun setSingleSelectIndex(name: String, index: Int) {
        _singleSelectIndex.value = Pair(name, index)
    }

    fun setNonSingleSelectIndexes(name: String, indexes: List<Int>) {
        _nonSingleSelectIndexes.value = Pair(name, indexes)
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted savedStateHandle: SavedStateHandle): FilterViewModel
    }
}