package com.acg.mangalive.catalog.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.acg.mangalive.catalog.data.model.MangaPageResponseDto
import com.acg.mangalive.catalog.data.toMangaCard
import com.acg.mangalive.catalog.domain.model.MangaCard
import com.acg.mangalive.catalog.domain.model.SortingParameters
import com.acg.mangalive.share.IoDispatcher
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class MangaCatalogPagingSource @AssistedInject constructor(
    @Assisted("sortingParameters") private val sortingParameters: SortingParameters,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val mangaService: MangaService,
) : PagingSource<Long, MangaCard>() {

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, MangaCard> =
        try {
            doResponse(params)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    private suspend fun doResponse(params: LoadParams<Long>): LoadResult<Long, MangaCard> {
        val pageNumber = params.key ?: MangaService.INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize.toLong()

        val response = withContext(ioDispatcher) {
            mangaService.getPage(
                pageNumber,
                pageSize,
                sortingParameters.criterion
            )
        }

        return checkResponse(pageNumber, response)
    }

    private fun checkResponse(
        pageNumber: Long,
        response: Response<MangaPageResponseDto>
    ): LoadResult<Long, MangaCard> =
        if (response.isSuccessful) {
            val cards = response.body()!!.cards.map { it.toMangaCard() }
            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
            val nextPageNumber = if (cards.isEmpty()) null else pageNumber + 1

            LoadResult.Page(cards, prevPageNumber, nextPageNumber)
        } else {
            LoadResult.Error(HttpException(response))
        }

    override fun getRefreshKey(state: PagingState<Long, MangaCard>): Long? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition) ?: return null

        return closestPage.prevKey?.plus(1) ?: closestPage.nextKey?.plus(1)
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("sortingParameters") sortingParameters: SortingParameters): MangaCatalogPagingSource
    }
}