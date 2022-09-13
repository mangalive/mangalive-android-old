package com.acg.mangalive.notifications.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.acg.mangalive.data.toNotificationsCard
import com.acg.mangalive.notifications.data.model.NotificationsResponseDto
import com.acg.mangalive.notifications.domain.model.NotificationsCard
import com.acg.mangalive.notifications.domain.model.SortingParametersNotifications
import com.acg.mangalive.share.IoDispatcher
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.HttpException
import retrofit2.Response


class NotificationsPagingSource @AssistedInject constructor(
    @Assisted("sortingParametersNotifications") private val sortingParametersNotifications: SortingParametersNotifications,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val notificationsService: NotificationsService,
) : PagingSource<Long, NotificationsCard>() {

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, NotificationsCard> =
        try {
            doResponse(params)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    private suspend fun doResponse(params: LoadParams<Long>): LoadResult<Long, NotificationsCard> {
        val pageNumber = params.key ?: NotificationsService.INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize.toLong()

        val response =
            notificationsService.getPage(
                pageNumber,
                pageSize,
                sortingParametersNotifications.criterion
            )

        return checkResponse(pageNumber, response)
    }

    private fun checkResponse(
        pageNumber: Long,
        response: Response<NotificationsResponseDto>
    ): LoadResult<Long, NotificationsCard> =
        if (response.isSuccessful) {
            val cards = response.body()!!.cards.map { it.toNotificationsCard() }
            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
            val nextPageNumber = if (cards.isEmpty()) null else pageNumber + 1

            LoadResult.Page(cards, prevPageNumber, nextPageNumber)
        } else {
            LoadResult.Error(HttpException(response))
        }

    override fun getRefreshKey(state: PagingState<Long, NotificationsCard>): Long? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition) ?: return null

        return closestPage.prevKey?.plus(1) ?: closestPage.nextKey?.plus(1)
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("sortingParametersNotifications") sortingParametersNotifications: SortingParametersNotifications): NotificationsPagingSource
    }
}