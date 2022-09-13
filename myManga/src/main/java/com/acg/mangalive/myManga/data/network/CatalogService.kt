package com.acg.mangalive.myManga.data.network

import androidx.annotation.IntRange
import com.acg.mangalive.myManga.data.model.MangaCardDto
import com.acg.mangalive.myManga.data.model.PageResponseDto
import com.acg.mangalive.myManga.domain.model.SortingCriterion
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatalogService {
    @GET("page/{pageNumber}")
    suspend fun getPage(
        @[Path("pageNumber") IntRange(from = 1)] pageNumber: Long = INITIAL_PAGE_NUMBER,
        @[Query("pageSize") IntRange(
            from = 1,
            to = MAX_PAGE_SIZE
        )] pageSize: Long = DEFAULT_PAGE_SIZE,
        @Query("sortingCriterion") sortingCriterion: SortingCriterion = SortingCriterion.CurrentlyReading,
    ): Response<PageResponseDto>

    companion object {
        const val INITIAL_PAGE_NUMBER: Long = 1
        const val MAX_PAGE_SIZE: Long = 20
        const val DEFAULT_PAGE_SIZE: Long = MAX_PAGE_SIZE
    }
}

class FakeCatalogService : CatalogService {
    override suspend fun getPage(
        pageNumber: Long,
        pageSize: Long,
        sortingCriterion: SortingCriterion
    ): Response<PageResponseDto> {
        val cards = mutableListOf<MangaCardDto>()

        for (i in 0..pageSize) {
            if (i % 3 == 0.toLong()) {
                cards.add(
                    i.toInt(),
                    MangaCardDto(
                        id = i + 1,
                        title = "Берсерк",
                        //rating = Random().nextFloat() * 5
                    )
                )
            }

            if (i % 3 == 1.toLong()) {
                cards.add(
                    i.toInt(),
                    MangaCardDto(
                        id = i + 1,
                        title = "Всеведущий читатель",
                        //rating = Random().nextFloat() * 5
                    )
                )
            }

            if (i % 3 == 2.toLong()) {
                cards.add(
                    i.toInt(),
                    MangaCardDto(
                        id = i + 1,
                        title = "Поднятие уровня в одиночку",
                        //rating = Random().nextFloat() * 5
                    )
                )
            }
        }

        return Response.success(
            PageResponseDto(
                status = "ok",
                message = "Manga page successfully created",
                cards = cards
            )
        )
    }
}