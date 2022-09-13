package com.acg.mangalive.notifications.data.network

import androidx.annotation.IntRange
import com.acg.mangalive.notifications.data.model.NotificationsCardDto
import com.acg.mangalive.notifications.data.model.NotificationsResponseDto
import com.acg.mangalive.notifications.domain.model.SortingCriterionNotifications
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationsService {
    @GET("page/{pageNumber}")
    suspend fun getPage(
        @[Path("pageNumber") IntRange(from = 1)] pageNumber: Long = INITIAL_PAGE_NUMBER,
        @[Query("pageSize") IntRange(
            from = 1,
            to = MAX_PAGE_SIZE
        )] pageSize: Long = DEFAULT_PAGE_SIZE,
        @Query("sortingCriterion") sortingCriterionNotifications: SortingCriterionNotifications = SortingCriterionNotifications.ForToday,
    ): Response<NotificationsResponseDto>

    companion object {
        const val INITIAL_PAGE_NUMBER: Long = 1
        const val MAX_PAGE_SIZE: Long = 20
        const val DEFAULT_PAGE_SIZE: Long = MAX_PAGE_SIZE
    }
}

class FakeNotificationsService : NotificationsService {
    override suspend fun getPage(
        pageNumber: Long,
        pageSize: Long,
        sortingCriterionNotifications: SortingCriterionNotifications
    ): Response<NotificationsResponseDto> {
        val cards = mutableListOf<NotificationsCardDto>()

        val titleOne = "Берсерк"
        val chapterOne = 19
        val dateOne = "2022.10.01"

        val titleTwo = "Всеведущий читатель"
        val chapterTwo = 119
        val dateTwo = "2022.09.26"

        val titleThree = "Поднятие уровня в одиночку"
        val chapterThree = 167
        val dateThree = "2022.11.17"

        for (i in 0..pageSize) {
            if (i % 3 == 0.toLong()) {
                cards.add(
                    i.toInt(),
                    NotificationsCardDto(
                        id = i + 1,
                        title = titleOne,
                        chapter = chapterOne,
                        date = dateOne
                    )
                )
            }

            if (i % 3 == 1.toLong()) {
                cards.add(
                    i.toInt(),
                    NotificationsCardDto(
                        id = i + 1,
                        title = titleTwo,
                        chapter = chapterTwo,
                        date = dateTwo
                    )
                )
            }

            if (i % 3 == 2.toLong()) {
                cards.add(
                    i.toInt(),
                    NotificationsCardDto(
                        id = i + 1,
                        title = titleThree,
                        chapter = chapterThree,
                        date = dateThree
                    )
                )
            }
        }



        return Response.success(
            NotificationsResponseDto(
                status = "ok",
                message = "Manga page successfully created",
                cards = cards
            )
        )
    }
}