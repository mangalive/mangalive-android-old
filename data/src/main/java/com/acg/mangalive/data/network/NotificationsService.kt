package com.acg.mangalive.data.network

import android.util.Log
import androidx.annotation.IntRange
import com.acg.mangalive.data.network.model.MangaPageResponseDto
import com.acg.mangalive.data.network.model.NotificationsCardDto
import com.acg.mangalive.data.network.model.NotificationsResponseDto
import com.acg.mangalive.domain.model.SortingCriterionNotifications
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

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

        //Памятка по мангам
//        val titleOne = "Берсерк"
//        val chapterOne = 19
//        val idOne = 0
//        val dateOne = 1.1
//
//        val titleTwo = "Всеведущий читатель"
//        val chapterTwo = "119"
//        val idTwo = 1
//        val dateTwo = 2.1
//
//        val titleThree = "Поднятие уровня в одиночку"
//        val chapterThree = "167"
//        val idThree = 2
//        val dateThree = 3.1

        for (i in 0..pageSize) {
            cards.add(
                i.toInt(),
                NotificationsCardDto(
                    id = i + 1,
                    title = "Берсерк",
                    chapter = 19,
                    date = "2022.11.11"
                )
            )
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