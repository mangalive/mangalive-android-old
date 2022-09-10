package com.acg.mangalive.viewModel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.acg.mangalive.R
import com.acg.mangalive.domain.model.MangaCard
import com.acg.mangalive.domain.model.SortingCriterion
import com.acg.mangalive.domain.model.SortingParameters
import com.acg.mangalive.domain.useCases.MangaCatalogUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

val DEFAULT_SORTING_CRITERION = SortingCriterion.popularity
val DEFAULT_SORTING_CRITERION_VALUE = R.string.sortingMenu_popularity

class CatalogViewModel @Inject constructor(private val mangaCatalogUseCase: MangaCatalogUseCase) :
    ViewModel() {
    private var _sortingParameters = MutableLiveData(SortingParameters(criterion = DEFAULT_SORTING_CRITERION))
    val sortingParameters: LiveData<SortingParameters> = _sortingParameters

    val catalog = sortingParameters.map(::newPager).switchMap { it.liveData }

    private fun newPager(sortingParameters: SortingParameters) =
        Pager(PagingConfig(5)) { mangaCatalogUseCase(sortingParameters) }

    fun setSortingCriterion(value: SortingCriterion) {
        _sortingParameters.value = _sortingParameters.value?.copy(criterion = value)
    }
}
